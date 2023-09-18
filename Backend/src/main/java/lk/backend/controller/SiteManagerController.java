package lk.backend.controller;

import lk.backend.entity.Material;
import lk.backend.entity.PurchaseOrder;
import lk.backend.service.SiteManagerService;
import lk.backend.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Site manager
 *
 * @author SE3070_WE_69
 * @version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping(value = CommonConstants.PROCUMENTARY + CommonConstants.SITE_MANAGER)
public class SiteManagerController {

    // Initialize service
    @Autowired
    private SiteManagerService siteManagerService;

    /**
     * Add item
     */

    @PostMapping(value = "/addItem")
    public ResponseEntity addItem(@RequestBody Material material) {
        return ResponseEntity.ok(siteManagerService.addItem(material));
    }

    /**
     * Add PR
     */

    @PostMapping(value = "/addPR")
    public ResponseEntity addPR(@RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.ok(siteManagerService.addPR(purchaseOrder));
    }

    /**
     * Get items
     */

    @GetMapping(value = "/getItems")
    public ResponseEntity getItems() {
        return ResponseEntity.ok(siteManagerService.getItems());
    }

    /**
     * Get PRs
     */

    @GetMapping(value = "/getPRs")
    public ResponseEntity getPRs() {
        return ResponseEntity.ok(siteManagerService.getPRs());
    }

    /**
     * Get item by if
     */

    @GetMapping(value = "/getItemById/{id}")
    public ResponseEntity getItemById(@PathVariable String id) {
        return ResponseEntity.ok(siteManagerService.getItemById(id));
    }

    /**
     * Remove item
     */

    @DeleteMapping(value = "/removeItem/{id}")
    public ResponseEntity removeItem(@PathVariable String id) {
        return ResponseEntity.ok(siteManagerService.removeItem(id));
    }

    /**
     * Remove material
     */

    @DeleteMapping(value = "/removeMaterial/{id}")
    public ResponseEntity removeMaterial(@PathVariable String id) {
        return ResponseEntity.ok(siteManagerService.removeMaterial(id));
    }

    /**
     * Remove material
     */

    @PostMapping(value = "/editInfo")
    public ResponseEntity editInfo(@RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.ok(siteManagerService.editInfo(purchaseOrder));
    }
}
