package lk.backend.controller;

import lk.backend.entity.PurchaseOrderDetail;
import lk.backend.service.ProcumentOfficerService;
import lk.backend.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Procument Officer
 *
 * @author SE3070_WE_69
 * @version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping(value = CommonConstants.PROCUMENTARY + CommonConstants.PROCUMENT_OFFICER)
public class ProcumentOfficerController {

    // Initialize service
    @Autowired
    private ProcumentOfficerService procumentOfficerService;

    /**
     * Update PR
     */

    @PostMapping(value = "/updatePR")
    public ResponseEntity updatePR(@RequestBody PurchaseOrderDetail purchaseOrderDetail) {
        return ResponseEntity.ok(procumentOfficerService.updatePR(purchaseOrderDetail));
    }

    /**
     * Delete PR
     */

    @DeleteMapping(value = "/removePR/{itemId}")
    public ResponseEntity removePR(@PathVariable String itemId) {
        return ResponseEntity.ok(procumentOfficerService.removePR(itemId));
    }

    /**
     * Approve Order
     */

    @GetMapping(value = "/approveOrder/{orderId}")
    public ResponseEntity approveOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(procumentOfficerService.approveOrder(orderId));
    }

    /**
     * Get Suppliers
     */

    @GetMapping(value = "/getSuppliers")
    public ResponseEntity getSuppliers() {
        return ResponseEntity.ok(procumentOfficerService.getSuppliers());
    }

    /**
     * Send order to suppliers
     */

    @GetMapping(value = "/sendSupplier/{purchaseOrder}/{supplier}")
    public ResponseEntity sendSupplier(@PathVariable String purchaseOrder, @PathVariable String supplier) {
        return ResponseEntity.ok(procumentOfficerService.sendSupplier(purchaseOrder, supplier));
    }

    /**
     * View Quotations
     */

    @GetMapping(value = "/viewQuotations/{companyId}")
    public ResponseEntity viewQuotations(@PathVariable String companyId) {
        return ResponseEntity.ok(procumentOfficerService.viewQuotations(companyId));
    }

    /**
     * Get quotations from suppliers
     */

    @GetMapping(value = "/quotationSuppliers/{poId}")
    public ResponseEntity quotationSuppliers(@PathVariable String poId) {
        return ResponseEntity.ok(procumentOfficerService.quotationSuppliers(poId));
    }

    /**
     * Get quotation details
     */

    @GetMapping(value = "/quotationDetails/{poId}/{supplierId}")
    public ResponseEntity quotationDetails(@PathVariable String poId, @PathVariable String supplierId) {
        return ResponseEntity.ok(procumentOfficerService.quotationDetails(poId, supplierId));
    }

    /**
     * Finalize the supplier
     */

    @GetMapping(value = "/finalizeSupplier/{poId}/{supplierId}")
    public ResponseEntity finalizeSupplier(@PathVariable String poId, @PathVariable String supplierId) {
        return ResponseEntity.ok(procumentOfficerService.finalizeSupplier(poId, supplierId));
    }

    /**
     * Delete PR
     */

    @DeleteMapping(value = "/removePurchaseOrder/{orderId}")
    public ResponseEntity removePurchaseOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(procumentOfficerService.removePurchaseOrder(orderId));
    }

    /**
     * Reject PR
     */

    @DeleteMapping(value = "/rejectPurchaseOrder/{orderId}")
    public ResponseEntity rejectPurchaseOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(procumentOfficerService.rejectPurchaseOrder(orderId));
    }

    /**
     * Pay Order
     */

    @GetMapping(value = "/payOrder/{orderId}")
    public ResponseEntity payOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(procumentOfficerService.payOrder(orderId));
    }
}
