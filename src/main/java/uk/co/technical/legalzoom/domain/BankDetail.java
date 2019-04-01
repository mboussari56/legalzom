package uk.co.technical.legalzoom.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import uk.co.technical.legalzoom.util.NumberEncoder;

/*
 * A domain representation 
 */

@Table
@Entity
@EqualsAndHashCode(of = "cardNumber")
public class BankDetail implements Serializable, Comparable<BankDetail> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @NotNull
    @Size(min = 2, max = 30)
    private String bankName;

    @Column
    @NotNull
    @Digits(integer=16, fraction=0)
    private BigInteger cardNumber;

   
    @Column
    @NotNull
    @Min(2019)
    private int expiryYear;

    @Column
    @NotNull
    @Min(1)
    @Max(12)
    private int expiryMonth;

    private String encodedCardNumber;

    private String expiryDate;

    public BankDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BankDetail(String bankName, BigInteger cardNumber, int expiryMonth, int expiryYear) {
        super();
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigInteger getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(BigInteger cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getEncodedCardNumber() {
        return NumberEncoder.hideNumber(cardNumber);
    }

    @Override
    public int compareTo(BankDetail o) {
        // TODO Auto-generated method stub
        return LocalDate.of(o.getExpiryYear(), o.getExpiryMonth(), 1).compareTo(LocalDate.of(expiryYear, expiryMonth, 1));
    }

    @Override
    public String toString() {
        return "BankDetail [bankName=" + bankName + ", cardNumber=" + cardNumber + ", expiryYear=" + expiryYear + ", expiryMonth=" + expiryMonth + ", encodedCardNumber=" + encodedCardNumber + "]";
    }

    public String getExpiryDate() {
        return Month.valueOf(expiryMonth).toString() + "-" + expiryYear;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    private enum Month {
		// Represents month of the year
        JAN(1),
        FEB(2),
        MAR(3),
        APR(4),
        MAY(5),
        JUN(6),
        JLY(7),
        AUG(8),
        SEPT(9),
        OCT(10),
        NOV(11),
        DEC(12);

        private final int value;

        Month(int value) {
            this.value = value;
        }
		// A month is selected based on the int value passed
        public static Month valueOf(int value) {
            for (Month m : Month.values()) {
                if (m.value == value) {
                    return m;
                }

            }
            return null;
        }
    }
}
