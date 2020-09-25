package api.service.repository;

import api.service.model.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerRepo {
    private final List<Customer> customerList = new ArrayList<>();

    public CustomerRepo(){
        customerList.add(new Customer(1, "Jhon Doe", "kerkstraat", "5926 DF", "Asten"));
        customerList.add(new Customer(2, "Arnold Schwarzenegger", "titanusstraat", "6943 RC", "Geldrop"));
        customerList.add(new Customer(3, "Tommy Blinder", "zonnenbloemlaan", "3496 PL", "heeze"));
        customerList.add(new Customer(4, "jhon snow", "st.martinlaan", "8512 BM", "Eindhoven"));
    }

    public List<Customer> getCustomers() {
        return customerList;
    }

    public List<Customer> getCustomersByCity(String city) {
        List<Customer> filtered = new ArrayList<>();
        for (Customer customer : customerList) {
            if (customer.getCity().equals(city)) {
                filtered.add(customer);
            }
        }
        return filtered;
    }

    public Customer getCustomer(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id)
                return customer;
        }
        return null;
    }

    public boolean delete(int id) {
        Customer customer = getCustomer(id);
        if (customer == null){
            return false;
        }

        return customerList.remove(customer);
    }

    public boolean add(Customer customer) {
        if(customer.getId() == 0){
            int oldId = 0;
            for (Customer customer1 : customerList){
                int newId = customer1.getId();
                if(oldId < newId){
                    oldId = newId;
                }
            }

            customer = new Customer(
                    (oldId+1),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getZipcode(),
                    customer.getCity()
            );
        }

        if (this.getCustomer(customer.getId()) != null){
            return false;
        }
        customerList.add(customer);
        return true;
    }

    public boolean update(int id, Customer customer) {
        Customer old = this.getCustomer(id);
        if (old == null) {
            return false;
        }

        old.setName(customer.getName());
        old.setAddress(customer.getAddress());
        old.setZipcode(customer.getZipcode());
        old.setCity(customer.getCity());
        
        return true;
    }
}
