package lk.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import lk.backend.entity.PurchaseOrder;
import lk.backend.entity.PurchaseOrderDetail;
import lk.backend.entity.Quotation;
import lk.backend.entity.QuotationDetail;
import lk.backend.repository.PurchaseOrderRepository;
import lk.backend.repository.QuotationRepository;
import lk.backend.service.SupplierService;
import lk.backend.service.factory.OrderFactory;
import lk.backend.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private QuotationRepository quotationRepository;
    private OrderFactory orderFactory = OrderFactory.getOrderFactory();

    @Override
    public List<PurchaseOrder> getSupplierOrders(String supplierId) {
        return orderFactory.getOrderObj(CommonConstants.SUPPLIER_ORDER).getOrders(purchaseOrderRepository, supplierId);
    }

    @Override
    public boolean acceptOrder(String orderId) {
        return orderFactory.getOrderObj(CommonConstants.PURCHASE_ORDER).acceptOrder(purchaseOrderRepository, orderId);
//        Optional<PurchaseOrder> orderOptional = purchaseOrderRepository.findById(orderId);
//        if (orderOptional.isPresent()) {
//            PurchaseOrder purchaseOrder = orderOptional.get();
//            purchaseOrder.setPoAccepted(true);
//            purchaseOrderRepository.save(purchaseOrder);
//            return true;
//        }
//        return false;
    }

    @Override
    public PurchaseOrder finalizePurchaseOrder(PurchaseOrder purchaseOrder, String id, int rating) {
        return orderFactory.getOrderObj(CommonConstants.PURCHASE_ORDER).finalizePurchaseOrder(purchaseOrderRepository, purchaseOrder, id);
//        Optional<PurchaseOrder> orderOptional = purchaseOrderRepository.findById(id);
//        if (orderOptional.isPresent()) {
//            PurchaseOrder purchaseOrderObj = orderOptional.get();
//            purchaseOrderObj.setPurchaseOrderDetails(purchaseOrder.getPurchaseOrderDetails());
//            for (PurchaseOrderDetail purchaseOrderDetaiObj : purchaseOrderObj.getPurchaseOrderDetails()) {
//                purchaseOrderDetaiObj.setPurchaseOrder(purchaseOrderObj);
//            }
//            purchaseOrderObj.setPoFinalized(true);
//            purchaseOrderObj.setIdFormatted(purchaseOrderObj.getFormattedId());
//            purchaseOrderRepository.save(purchaseOrderObj);
//            return new PurchaseOrder(purchaseOrderObj);
//        }
//        return null;
    }

    @Override
    public List<PurchaseOrder> getQuotations(String supplierId) {
        List<Quotation> quotations = quotationRepository.getAllBySupplierId(supplierId);
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        for (Quotation quotation : quotations) {
            PurchaseOrder purchaseOrderObj = new PurchaseOrder(quotation.getPurchaseOrder(), null, null, quotation.getPurchaseOrder().getSiteManager());
            List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
            for (QuotationDetail quotationDetail : quotation.getQuotationDetails()) {
                PurchaseOrderDetail purchaseOrderDetailObj = new PurchaseOrderDetail(quotationDetail.getPurchaseOrderDetail());
                purchaseOrderDetailObj.setQuotationDetailId(quotationDetail.getId());
                purchaseOrderDetailObj.setSoQuantity(quotationDetail.getSoQuantity());
                purchaseOrderDetailObj.setSoUnitPrice(quotationDetail.getSoUnitPrice());
                purchaseOrderDetailObj.setStatus(quotationDetail.getStatus());
                purchaseOrderObj.setSoTotal(purchaseOrderObj.getSoTotal() + (quotationDetail.getSoUnitPrice() * quotationDetail.getSoQuantity()));
                purchaseOrderDetails.add(purchaseOrderDetailObj);
            }
            purchaseOrderObj.setPurchaseOrderDetailList(purchaseOrderDetails);
            purchaseOrderList.add(purchaseOrderObj);
        }
        return purchaseOrderList;
    }

    @Override
    public boolean finalizeQuotation(PurchaseOrder purchaseOrder, String supplierId) {
        Quotation quotation = quotationRepository.getAllByPurchaseOrderIdAndSupplierId(purchaseOrder.getId(), supplierId);
        for (QuotationDetail quotationDetail : quotation.getQuotationDetails()) {
            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrder.getPurchaseOrderDetails()) {
                if (quotationDetail.getPurchaseOrderDetail().getId().equals(purchaseOrderDetail.getId())) {
                    quotationDetail.setSoQuantity(purchaseOrderDetail.getSoQuantity());
                    quotationDetail.setSoUnitPrice(purchaseOrderDetail.getSoUnitPrice());
                    quotationDetail.setStatus(purchaseOrderDetail.getStatus());
                }
            }
        }
        quotationRepository.save(quotation);
        return true;
    }

    @Override
    public boolean deliverOrder(String orderId) {
        return orderFactory.getOrderObj(CommonConstants.SUPPLIER_ORDER).deliverOrder(purchaseOrderRepository, orderId);
    }
}
