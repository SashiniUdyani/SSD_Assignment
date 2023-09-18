package lk.backend.controller;

import lk.backend.entity.PurchaseOrder;
import lk.backend.service.WarehouseService;
import lk.backend.service.command.FinalizeOrderCommand;
import lk.backend.service.command.FinalizeSupplierOrder;
import lk.backend.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Warehouse manager
 *
 * @author SE3070_WE_69
 * @version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping(value = CommonConstants.PROCUMENTARY + CommonConstants.WAREHOUSE_MANAGER)
public class WarehouseController {

    // Initialize service
    @Autowired
    private WarehouseService warehouseService;
    // Initialize order command
    private FinalizeOrderCommand finalizeOrderCommand = new FinalizeOrderCommand();

    /**
     * FGet Finalize SupplierOrders
     */

    @GetMapping(value = "/getFinalizedSupplierOrders/{warehouseId}")
    public ResponseEntity getFinalizedPurchaseOrders(@PathVariable String warehouseId) {
        return ResponseEntity.ok(warehouseService.getFinalizedPurchaseOrders(warehouseId));
    }

    /**
     * Finalize SupplierOrder
     */

    @GetMapping(value = "/finalizeSupplierOrder/{warehouseId}/{rating}")
    public ResponseEntity finalizeSupplierOrder(@PathVariable String warehouseId, @PathVariable int rating) {
//        return ResponseEntity.ok(warehouseService.finalizeSupplierOrder(purchaseOrder,warehouseId));
        finalizeOrderCommand.commandSupplierOrder = new FinalizeSupplierOrder(warehouseService);
        return ResponseEntity.ok(finalizeOrderCommand.commandSupplierOrder.finalizeOrder(null, warehouseId, rating));
    }

}
