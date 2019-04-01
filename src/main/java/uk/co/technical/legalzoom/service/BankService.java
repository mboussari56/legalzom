package uk.co.technical.legalzoom.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.technical.legalzoom.domain.BankDetail;
import uk.co.technical.legalzoom.repository.BankDetailRepository;

@Service
public class BankService {

    @Autowired
    private BankDetailRepository bankDetailRepository;

    public List<BankDetail> retrieveAllRecords() {
        List<BankDetail> result = bankDetailRepository.findAll();
        Collections.sort(result);
        return result;
    }

    public Optional<BankDetail> getBankDetails(Integer id) {
        return bankDetailRepository.findById(id);
    }

    public void saveOrUpdate(BankDetail bankDetail) {
        BankDetail result = bankDetailRepository.findByCardNumber(bankDetail.getCardNumber());
        if (result != null) return;
        bankDetailRepository.save(bankDetail);
    }

    public void saveOrUpdate(Set<BankDetail> bankDetails) {
        bankDetailRepository.saveAll(bankDetails);
    }

    public void deleteAll() {
        bankDetailRepository.deleteAll();
    }

    public void saveRecordFromCsv(byte[] bytes) throws IllegalArgumentException, IOException {
        if (bytes == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        Set<BankDetail> bankDetails = new HashSet<BankDetail>(); // to ensure duplicates are eliminated

        BufferedReader reader = null;
        InputStream inputStream = null;

        try {
            inputStream = new ByteArrayInputStream(bytes);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(",");
                if (fields.length < 4) {
                    // log exception or throw exception
                    continue;
                }
                bankDetails.add(new BankDetail(fields[0], new BigInteger(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3])));
            }

        } catch (IOException e) {
            // log error

        } finally {
            reader.close();
            inputStream.close();
        }

        bankDetailRepository.saveAll(bankDetails);
    }

}
