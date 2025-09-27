package io.eaterythem.eaterythem.tools;

import io.eaterythem.eaterythem.model.*;
import io.eaterythem.eaterythem.model.enums.*;
import io.eaterythem.eaterythem.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private FriendshipRepository friendshipRepo;
    @Autowired
    private RecipeRepository recipeRepo;
    @Autowired
    private CycleRepository cycleRepo;
    @Autowired
    private PlanRepository planRepo;
    @Autowired
    private CycleRecipeRepository cycleRecipeRepo;
    @Autowired
    private EntryRepository entryRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepo.count() > 0) {
            System.out.println("Seed data already exists. Skipping.");
            return;
        }

        // ===== Create user =====
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword(passwordEncoder.encode("pass1234"));
        user = userRepo.save(user);

        // Create second user to share with
        User jane = new User();
        jane.setName("Jane Smith");
        jane.setEmail("jane@example.com");
        jane.setPassword(passwordEncoder.encode("pass5678"));

        jane = userRepo.save(jane);
        
        friendshipRepo.save(new Friendship(null, jane, user, FriendshipStatus.ACCEPTED, LocalDateTime.now()));
        friendshipRepo.save(new Friendship(null, user, jane, FriendshipStatus.BLOCKED, LocalDateTime.now()));
        friendshipRepo.save(new Friendship(null, jane, user, FriendshipStatus.PENDING, LocalDateTime.now()));
        
        // ===== Create recipes =====
        Recipe r1 = new Recipe();
        r1.setName("Pancakes");
        r1.setInstructions("Mix and cook");
        r1.setIngredients("Flour, Milk");
        r1.setMealType(MealType.BREAKFAST);
        r1.setUser(user);

        Recipe r2 = new Recipe();
        r2.setName("Chicken Salad");
        r2.setInstructions("Mix and serve");
        r2.setIngredients("Chicken, Veggies");
        r2.setMealType(MealType.LUNCH);
        r2.setUser(user);

        Recipe r3 = new Recipe();
        r3.setName("Spaghetti");
        r3.setInstructions("Boil and sauce");
        r3.setIngredients("Pasta, Tomato");
        r3.setMealType(MealType.DINNER);
        r3.setUser(user);

        recipeRepo.saveAll(List.of(r1, r2, r3));

        // ===== Create  cycles =====
        Cycle breakfastCycle = new Cycle();
        breakfastCycle.setName("BfastCycle");
        breakfastCycle.setUser(user);
        breakfastCycle.setMealType(MealType.BREAKFAST);
        breakfastCycle.setPublic(false);

        Cycle lunchCycle = new Cycle();
        lunchCycle.setName("LunchCycle");
        lunchCycle.setUser(user);
        lunchCycle.setMealType(MealType.LUNCH);
        lunchCycle.setPublic(false);

        Cycle dinnerCycle = new Cycle();
        dinnerCycle.setName("DinnerCycle");
        dinnerCycle.setUser(user);
        dinnerCycle.setMealType(MealType.DINNER);
        dinnerCycle.setPublic(false);

        cycleRepo.saveAll(List.of(breakfastCycle, lunchCycle, dinnerCycle));

        // Share breakfast cycle with Jane
        breakfastCycle.setSharedWith(List.of(jane));
        cycleRepo.save(breakfastCycle);

        // ===== Add recipes to cycles =====
        CycleRecipe bfastCycleRecipe = new CycleRecipe();
        bfastCycleRecipe.setCycle(breakfastCycle);
        bfastCycleRecipe.setRecipe(r1);
        bfastCycleRecipe.setPosition(0);

        CycleRecipe lunchCycleRecipe = new CycleRecipe();
        lunchCycleRecipe.setCycle(lunchCycle);
        lunchCycleRecipe.setRecipe(r2);
        lunchCycleRecipe.setPosition(0);

        CycleRecipe dinnerCycleRecipe = new CycleRecipe();
        dinnerCycleRecipe.setCycle(dinnerCycle);
        dinnerCycleRecipe.setRecipe(r3);
        dinnerCycleRecipe.setPosition(0);

        cycleRecipeRepo.saveAll(List.of(bfastCycleRecipe, lunchCycleRecipe, dinnerCycleRecipe));

        // ===== Create a  plan =====
        Plan plan = new Plan();
        List<PlanParticipant> pp = new ArrayList<PlanParticipant>();
        pp.add(PlanParticipant.builder()
                .user(user)
                .Plan(plan)
                .role(ParticipantRole.OWNER)
                .build());

        plan.setName("plan 1");
        plan.setParticipants(pp);
        plan.setStartDate(LocalDate.now());
        plan.setStatus(PlanStatus.ACTIVE);
        plan.setBreakfastCycle(breakfastCycle);
        plan.setLunchCycle(lunchCycle);
        plan.setDinnerCycle(dinnerCycle);
        plan.setBreakfastIndex(0);
        plan.setLunchIndex(0);
        plan.setDinnerIndex(0);
        plan = planRepo.save(plan);

        // ===== Create a  entry =====
        Entry entry = Entry.builder().Plan(plan).dayIndex(0).build();
        entry.setPlannedRecipe(r1);
        entry.setActualRecipe(r1);
        entry.setStatus(EntryStatus.COMPLETED);
        entry.setNote("Tasted good");
        entryRepo.save(entry);

        System.out.println("✅ Seed data loaded successfully.");
    }
}
