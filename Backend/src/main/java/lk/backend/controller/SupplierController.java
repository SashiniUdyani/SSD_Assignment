package lk.backend.controller;

import java.util.List;

import lk.backend.entity.PurchaseOrder;
import lk.backend.service.SupplierService;
import lk.backend.service.command.FinalizeOrderCommand;
import lk.backend.service.command.FinalizePurchaseOrder;
import lk.backend.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Supplier
 *
 * @author SE3070_WE_69
 * @version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping(value = CommonConstants.PROCUMENTARY + CommonConstants.SUPPLIER)
public class SupplierController {

    // Initialize service
    @Autowired
    private SupplierService supplierService;
    // Initialize order command
    private FinalizeOrderCommand finalizeOrderCommand = new FinalizeOrderCommand();

    /**
     * Finalize PurchaseOrder
     */

    @PutMapping(value = "/finalizePurchaseOrder/{id}")
    public ResponseEntity finalizePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder, @PathVariable String id) {
        finalizeOrderCommand.commandPurchaseOrder = new FinalizePurchaseOrder(supplierService);
        return ResponseEntity.ok(finalizeOrderCommand.commandPurchaseOrder.finalizeOrder(purchaseOrder, id, 0));
    }

    /**
     * Finalize Quotation
     */

    @PutMapping(value = "/finalizeQuotation/{id}")
    public ResponseEntity finalizeQuotation(@RequestBody PurchaseOrder purchaseOrder, @PathVariable String id) {
        return ResponseEntity.ok(supplierService.finalizeQuotation(purchaseOrder, id));
    }

    /**
     * Get purchase orders
     */

    @GetMapping(value = "/getPurchaseOrders/{supplierId}")
    public List<PurchaseOrder> getSupplierOrders(@PathVariable String supplierId) {
        return supplierService.getSupplierOrders(supplierId);
    }

    /**
     * Get quotations
     */

    @GetMapping(value = "/getQuotations/{supplierId}")
    public List<PurchaseOrder> getQuotations(@PathVariable String supplierId) {
        return supplierService.getQuotations(supplierId);
    }

    /**
     * Accept order
     */

    @GetMapping(value = "/acceptOrder/{orderId}")
    public boolean acceptOrder(@PathVariable String orderId) {
        return supplierService.acceptOrder(orderId);
    }

    /**
     * Accept order
     */

    @GetMapping(value = "/deliverOrder/{orderId}")
    public boolean deliverOrder(@PathVariable String orderId) {
        return supplierService.deliverOrder(orderId);
    }

}
