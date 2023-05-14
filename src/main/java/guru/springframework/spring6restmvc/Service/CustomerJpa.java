package guru.springframework.spring6restmvc.Service;

import guru.springframework.spring6restmvc.Customer.CustomerDto;
import guru.springframework.spring6restmvc.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import guru.springframework.spring6restmvc.Repositories.CustomerRepostiory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class CustomerJpa implements ServiceIntefaceforGet {
    private final CustomerRepostiory customerRepository;
    private  final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> returnCustomer() {
      return customerRepository.findAll().stream().
              map(customerMapper::CustomerDTOtoCustomer).collect(Collectors.toList());
//public List<CustomerDto> returnCustomer(): This line declares a method named returnCustomer()
// that returns a list of CustomerDto objects.
//
//customerRepository.findAll():
// This code retrieves all customer records from the customerRepository.
// The findAll() method returns a list of Customer objects.
//
//.stream(): This code converts the list of Customer
// objects into a stream. A stream is a sequence of elements
// that can be processed in a pipeline.
//
//.map(customerMapper::CustomerDTOtoCustomer): This line applies the CustomerDTOtoCustomer
// method from the customerMapper object to each Customer object in the stream.
// It maps each Customer object to a CustomerDto object.
//
//.collect(Collectors.toList()): This code collects the mapped CustomerDto
// objects from the stream and gathers them into a list.
//
//The resulting list of CustomerDto objects is then returned as the result of the returnCustomer() method.
//
//In summary, the returnCustomer() method retrieves all customer records from the
// customerRepository, maps each Customer object to a CustomerDto object
// using the CustomerDTOtoCustomer method provided by the customerMapper object,
// and returns a list of CustomerDto objects representing the transformed customer data.
    }
    @Override
    public CustomerDto returnCustomerOfId(UUID id) {
        CustomerDto a;
        a = customerMapper.CustomerDTOtoCustomer(customerRepository.findById(id).get());
        if (a == null) {
            throw new RuntimeException("Customer not found");
        } else {
            return a;
        }
    }

    @Override
    public CustomerDto HandlePost(CustomerDto customerDto) {
        return customerMapper.CustomerDTOtoCustomer(customerRepository.save(customerMapper.CustomertoCustomerDTO(customerDto)));
    }

    @Override
    public void updatebyId(UUID id, CustomerDto customerDto) {
      customerRepository.findById(id).ifPresent(customer -> { //finding by Id
          //if present then it update it +
          customer.setCustomername(customerDto.getCustomername());
          customer.setCustomerDate(customerDto.getCustomerDate());
          customer.setCustomerVersion(customerDto.getCustomerVersion());
          customer.setLastModfiedDate(customerDto.getLastModfiedDate());
          customerRepository.save(customer);
        });
    }

    @Override
    public void DeletebyId(UUID id) {
     customerRepository.deleteById(id);
    }
    @Override
    public void DeleteAll() {
  customerRepository.deleteAll();
    }
}
