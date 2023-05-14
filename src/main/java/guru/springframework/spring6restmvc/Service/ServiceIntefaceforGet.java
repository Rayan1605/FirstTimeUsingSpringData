package guru.springframework.spring6restmvc.Service;

import guru.springframework.spring6restmvc.Customer.CustomerDto;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ServiceIntefaceforGet {

    public List<CustomerDto> returnCustomer();

    public CustomerDto returnCustomerOfId(UUID id);

    CustomerDto HandlePost(CustomerDto customerDto);

    void updatebyId(UUID id, CustomerDto customerDto);

    void DeletebyId(UUID id);

    void DeleteAll();
}
