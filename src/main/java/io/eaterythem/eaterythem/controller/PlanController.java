package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.PlanDTO;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.service.PlanService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/plans")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public List<PlanDTO> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public PlanDTO getPlanById(@PathVariable Integer id) {
        return planService.getPlanById(id);
    }

    @GetMapping("/me")
    public List<PlanDTO> getMePlans(@CurrentUser UserPrincipal user) {
        return planService.getMePlans(user.getUserId());
    }
    

    @PostMapping
    public PlanDTO createPlan(@RequestBody PlanDTO planDTO, @CurrentUser UserPrincipal user) {
        return planService.createPlan(planDTO, user.getUserId());
    }

    @PatchMapping("/{id}")
    public PlanDTO updatePlan(@PathVariable Integer id, @RequestBody PlanDTO planDTO, @CurrentUser UserPrincipal user) {
        return planService.updatePlan(id, planDTO, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Integer id, @CurrentUser UserPrincipal user) {
        planService.deletePlan(id, user.getUserId());
    }
} 