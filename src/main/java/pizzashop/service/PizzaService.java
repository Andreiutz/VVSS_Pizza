package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    private boolean paymentIsValid(Payment payment) {
        if (payment.getTableNumber() < 1 || payment.getTableNumber() > 9) {
            return false;
        }
        if (payment.getType() == null) {
            return false;
        }
        if (payment.getAmount() <= 0.0f || payment.getAmount() >= 2000.0f) {
            return false;
        }

        return true;
    }

    public void addPayment(int table, PaymentType type, double amount){
        Payment payment= new Payment(table, type, amount);
        if(paymentIsValid(payment)) {
            payRepo.add(payment);
        }
    }

    public double getTotalAmount(PaymentType type){
        return getTotalAmount(getPayments(),type);
    }

    public double getTotalAmount(List<Payment> l, PaymentType type){
        double total=0.0f;
        if (l==null)
            return total;
        if (l.size()==0)
            return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }
}
