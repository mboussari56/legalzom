package uk.co.technical.legalzoom.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import uk.co.technical.legalzoom.domain.BankDetail;

public interface BankDetailRepository extends CrudRepository<BankDetail, Integer>  {

    BankDetail findByCardNumber(String number);
    List<BankDetail> findAll();
}
