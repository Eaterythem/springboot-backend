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
    private final PlanService PlanService;

    public PlanController(PlanService PlanService) {
        this.PlanService = PlanService;
    }

    @GetMapping
    public List<PlanDTO> getAllPlans() {
        return PlanService.getAllPlans();
    }

    @GetMapping("/{id}")
    public PlanDTO getPlanById(@PathVariable Integer id) {
        return PlanService.getPlanById(id);
    }

    @GetMapping("/me")
    public List<PlanDTO> getMePlans(@CurrentUser UserPrincipal user) {
        return PlanService.getMePlans(user.getUserId());
    }
    

    @PostMapping
    public PlanDTO createPlan(@RequestBody PlanDTO PlanDTO, @CurrentUser UserPrincipal user) {
        return PlanService.createPlan(PlanDTO, user.getUserId());
    }

    @PutMapping("/{id}")
    public PlanDTO updatePlan(@PathVariable Integer id, @RequestBody PlanDTO PlanDTO, @CurrentUser UserPrincipal user) {
        return PlanService.updatePlan(id, PlanDTO, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Integer id, @CurrentUser UserPrincipal user) {
        PlanService.deletePlan(id, user.getUserId());
    }
} 