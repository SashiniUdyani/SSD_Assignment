package lk.backend.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lk.backend.entity.*;
import lk.backend.repository.*;
import lk.backend.service.ProcumentOfficerService;
import lk.backend.service.factory.OrderFactory;
import lk.backend.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcumentOfficerServiceImpl implements ProcumentOfficerService {

    @Autowired
    private PurchaseOrderDetailRepository purchaseOrderDetailRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuotationRepository quotationRepository;
    private OrderFactory orderFactory = OrderFactory.getOrderFactory();

    @Override
    public boolean removePR(String itemId) {
        return orderFactory.getOrderObj(CommonConstants.PURCHASE_ORDER).removePR(purchaseOrderDetailRepository, itemId);
//        purchaseOrderDetailRepository.deleteById(itemId);
//        return true;
    }

    @Override
    public PurchaseOrderDetail updatePR(PurchaseOrderDetail purchaseOrderDetail) {
        return orderFactory.getOrderObj(CommonConstants.PURCHASE_ORDER).updatePR(purchaseOrderDetailRepository, purchaseOrderDetail);
//        purchaseOrderDetail.setStatus("Incomplete");
//        purchaseOrderDetail = purchaseOrderDetailRepository.save(purchaseOrderDetail);
//        return new PurchaseOrderDetail(purchaseOrderDetail);
    }

    @Override
    public boolean approveOrder(String orderId) {
        return orderFactory.getOrderObj(CommonConstants.PURCHASE_ORDER).approveOrder(purchaseOrderRepository, orderId);
//        Optional<PurchaseOrder> orderOptional = purchaseOrderRepository.findById(orderId);
//        if (orderOptional.isPresent()) {
//            PurchaseOrder purchaseOrder = orderOptional.get();
//            purchaseOrder.setPoApproved(true);
//            purchaseOrderRepository.save(purchaseOrder);
//            return true;
//        }
//        return false;
    }

    @Override
    public List<AppUser> getSuppliers() {
        return userRepository.getAllByUserType("supplier");
    }

    @Override
    public boolean sendSupplier(String purchaseOrderId, String supplierId) {
        Quotation quotation = new Quotation();
        quotation.setId(purchaseOrderId + supplierId);
        quotation.setPurchaseOrder(purchaseOrderRepository.findById(purchaseOrderId).get());
        quotation.setSupplier(userRepository.findById(supplierId).get());
        quotation.setQuotationDetails(new HashSet<>());
        for (PurchaseOrderDetail purchaseOrderDetail : quotation.getPurchaseOrder().getPurchaseOrderDetails()) {
            QuotationDetail quotationDetail = new QuotationDetail();
            quotationDetail.setId(purchaseOrderDetail.getId() + supplierId);
            quotationDetail.setPurchaseOrderDetail(purchaseOrderDetail);
            quotationDetail.setQuotation(quotation);
            quotationDetail.setSoUnitPrice(purchaseOrderDetail.getSoUnitPrice());
            quotationDetail.setSoQuantity(purchaseOrderDetail.getSoQuantity());
            quotationDetail.setStatus("Incomplete");
            quotation.getQuotationDetails().add(quotationDetail);
        }
        quotationRepository.save(quotation);
        return true;
    }

    @Override
    public List<PurchaseOrder> viewQuotations(String companyId) {
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        List<Quotation> quotationList = quotationRepository.getAllByPurchaseOrderSiteManagerCompanyCompanyId(companyId);
        for (Quotation quotation : quotationList) {
            purchaseOrderList.add(new PurchaseOrder(quotation.getPurchaseOrder()));
        }
        return purchaseOrderList;
    }

    @Override
    public List<AppUser> quotationSuppliers(String poId) {
        List<AppUser> appUsers = new ArrayList<>();
        List<Quotation> quotationList = quotationRepository.getAllByPurchaseOrderId(poId);
        for (Quotation quotation : quotationList) {
            AppUser supplier = quotation.getSupplier();
            supplier.setPurchaseOrderDetailList(quotationDetails(poId, supplier.getId()));
            appUsers.add(supplier);
        }
        return appUsers;
    }

    @Override
    public List<PurchaseOrderDetail> quotationDetails(String poId, String supplierId) {
        Quotation quotation = quotationRepository.getAllByPurchaseOrderIdAndSupplierId(poId, supplierId);
        List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
        for (QuotationDetail quotationDetail : quotation.getQuotationDetails()) {
            PurchaseOrderDetail purchaseOrderDetail = quotationDetail.getPurchaseOrderDetail();
            purchaseOrderDetail.setSoUnitPrice(quotationDetail.getSoUnitPrice());
            purchaseOrderDetail.setSoQuantity(quotationDetail.getSoQuantity());
            purchaseOrderDetail.setStatus(quotationDetail.getStatus());
            purchaseOrderDetails.add(new PurchaseOrderDetail(purchaseOrderDetail));
        }
        return purchaseOrderDetails;
    }

    @Override
    public boolean finalizeSupplier(String poId, String supplierId) {
        Optional<PurchaseOrder> orderOptional = purchaseOrderRepository.findById(poId);
        Quotation quotation = quotationRepository.getAllByPurchaseOrderIdAndSupplierId(poId, supplierId);
        if (orderOptional.isPresent()) {
            PurchaseOrder purchaseOrder = orderOptional.get();
            purchaseOrder.setPoStatus("Finalized");
            purchaseOrder.setSupplier(userRepository.findById(supplierId).get());
            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrder.getPurchaseOrderDetails()) {
                for (QuotationDetail quotationDetail : quotation.getQuotationDetails()) {
                    if (quotationDetail.getPurchaseOrderDetail().getId().equals(purchaseOrderDetail.getId())) {
                        purchaseOrderDetail.setSoUnitPrice(quotationDetail.getSoUnitPrice());
                        purchaseOrderDetail.setSoQuantity(quotationDetail.getSoQuantity());
                        purchaseOrderDetail.setStatus(quotationDetail.getStatus());
                    }
                }
            }
            purchaseOrderRepository.save(purchaseOrder);
        }
        return true;
    }

    @Override
    public boolean removePurchaseOrder(String orderId) {
        return orderFactory.getOrderObj(CommonConstants.PURCHASE_ORDER).removePurchaseOrder(purchaseOrderRepository, orderId);
    }

    @Override
    public boolean rejectPurchaseOrder(String orderId) {
        return orderFactory.getOrderObj(CommonConstants.PURCHASE_ORDER).rejectPurchaseOrder(purchaseOrderRepository, orderId);
    }

    @Override
    public boolean payOrder(String orderId) {
        return orderFactory.getOrderObj(CommonConstants.PURCHASE_ORDER).payOrder(purchaseOrderRepository, orderId);
    }
}
