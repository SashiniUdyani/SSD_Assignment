package lk.backend.service.factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;
import lk.backend.repository.*;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderService implements OrderService {

    @Override
    public List<PurchaseOrder> getOrders(PurchaseOrderRepository purchaseOrderRepository, String id) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.getAllBySiteManagerCompanyCompanyIdAndPoFinalized(id, true);
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            PurchaseOrder purchaseOrderObj = new PurchaseOrder(purchaseOrder, null, purchaseOrder.getSupplier(), purchaseOrder.getSiteManager());
            List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrder.getPurchaseOrderDetails()) {
                purchaseOrderDetails.add(new PurchaseOrderDetail(purchaseOrderDetail));
            }
            purchaseOrderObj.setPurchaseOrderDetailList(purchaseOrderDetails);
            purchaseOrderList.add(purchaseOrderObj);
        }
        return purchaseOrderList;
    }

    @Override
    public PurchaseOrderDetail updatePR(PurchaseOrderDetailRepository purchaseOrderDetailRepository, PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrderDetail.setStatus("Incomplete");
        purchaseOrderDetail = purchaseOrderDetailRepository.save(purchaseOrderDetail);
        return new PurchaseOrderDetail(purchaseOrderDetail);
    }

    @Override
    public boolean approveOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        Optional<PurchaseOrder> orderOptional = purchaseOrderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            PurchaseOrder purchaseOrder = orderOptional.get();
            purchaseOrder.setPoStatus("Approved");
            purchaseOrder.setPoApproved(true);
            purchaseOrderRepository.save(purchaseOrder);
            return true;
        }
        return false;
    }

    @Override
    public boolean removePR(PurchaseOrderDetailRepository purchaseOrderDetailRepository, String itemId) {
        purchaseOrderDetailRepository.deleteById(itemId);
        return true;
    }

    @Override
    public boolean acceptOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        Optional<PurchaseOrder> orderOptional = purchaseOrderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            PurchaseOrder purchaseOrder = orderOptional.get();
            purchaseOrder.setPoAccepted(true);
            purchaseOrder.setSoStatus("Accepted");
            purchaseOrderRepository.save(purchaseOrder);
            return true;
        }
        return false;
    }

    @Override
    public PurchaseOrder finalizePurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder, String id) {
        Optional<PurchaseOrder> orderOptional = purchaseOrderRepository.findById(id);
        if (orderOptional.isPresent()) {
            PurchaseOrder purchaseOrderObj = orderOptional.get();
            purchaseOrderObj.setPurchaseOrderDetails(purchaseOrder.getPurchaseOrderDetails());
            for (PurchaseOrderDetail purchaseOrderDetaiObj : purchaseOrderObj.getPurchaseOrderDetails()) {
                purchaseOrderDetaiObj.setPurchaseOrder(purchaseOrderObj);
            }
            purchaseOrderObj.setPoFinalized(true);
            purchaseOrderObj.setSoStatus("Finalized");
            purchaseOrderObj.setIdFormatted(purchaseOrderObj.getFormattedId());
            purchaseOrderRepository.save(purchaseOrderObj);
            return new PurchaseOrder(purchaseOrderObj);
        }
        return null;
    }

    @Override
    public PurchaseOrder addPR(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder) {
        purchaseOrder.setId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
        purchaseOrder.setPoStatus("Pending");
        purchaseOrder.setSoStatus("Pending");
        for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrder.getPurchaseOrderDetails()) {
            purchaseOrderDetail.setId("PD" + purchaseOrder.getId() + purchaseOrderDetail.getId());
            purchaseOrderDetail.setStatus("Incomplete");
            purchaseOrderDetail.setPurchaseOrder(purchaseOrder);
        }
        purchaseOrderRepository.save(purchaseOrder);
        return new PurchaseOrder(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> getPRs(PurchaseOrderRepository purchaseOrderRepository) {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        List<PurchaseOrder> purchaseOrderListDTOs = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseOrderList) {
            PurchaseOrder purchaseOrderObj = new PurchaseOrder(purchaseOrder);
            purchaseOrderObj.setSupplier(purchaseOrder.getSupplier());
            List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrder.getPurchaseOrderDetails()) {
                purchaseOrderDetails.add(new PurchaseOrderDetail(purchaseOrderDetail));
            }
            purchaseOrderObj.setPurchaseOrderDetailList(purchaseOrderDetails);
            purchaseOrderListDTOs.add(purchaseOrderObj);
        }
        return purchaseOrderListDTOs;
    }

    @Override
    public boolean removeItem(PurchaseOrderDetailRepository purchaseOrderDetailRepository, String id) {
        purchaseOrderDetailRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean removePurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        purchaseOrderRepository.deleteById(orderId);
        return true;
    }

    @Override
    public boolean rejectPurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        purchaseOrderRepository.deleteById(orderId);
        return true;
    }

    @Override
    public boolean editInfo(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrder purchaseOrder) {
        PurchaseOrder purchaseOrderObj = purchaseOrderRepository.findById(purchaseOrder.getId()).get();
        purchaseOrderObj.setDeliverNote(purchaseOrder.getDeliverNote());
        purchaseOrderRepository.save(purchaseOrderObj);
        return true;
    }

    @Override
    public boolean deliverOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        return false;
    }

    @Override
    public boolean payOrder(PurchaseOrderRepository purchaseOrderRepository, String orderId) {
        PurchaseOrder purchaseOrderObj = purchaseOrderRepository.findById(orderId).get();
        purchaseOrderObj.setPoStatus("Paid");
        purchaseOrderObj.setSoStatus("Paid");
        purchaseOrderRepository.save(purchaseOrderObj);
        return true;
    }
}
