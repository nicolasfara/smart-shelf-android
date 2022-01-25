package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Product type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Products")
public final class Product implements Model {
  public static final QueryField ID = field("Product", "id");
  public static final QueryField CODE = field("Product", "code");
  public static final QueryField LOT = field("Product", "lot");
  public static final QueryField NAME = field("Product", "name");
  public static final QueryField PRICE = field("Product", "price");
  public static final QueryField PROMO_PRICE = field("Product", "promoPrice");
  public static final QueryField IN_PROMO = field("Product", "inPromo");
  public static final QueryField EXPIRATION_DATE = field("Product", "expirationDate");
  public static final QueryField PURCHASE_DATE = field("Product", "purchaseDate");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String code;
  private final @ModelField(targetType="Int", isRequired = true) Integer lot;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Float", isRequired = true) Double price;
  private final @ModelField(targetType="Float") Double promoPrice;
  private final @ModelField(targetType="Boolean") Boolean inPromo;
  private final @ModelField(targetType="AWSDate", isRequired = true) Temporal.Date expirationDate;
  private final @ModelField(targetType="AWSDate", isRequired = true) Temporal.Date purchaseDate;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getCode() {
      return code;
  }
  
  public Integer getLot() {
      return lot;
  }
  
  public String getName() {
      return name;
  }
  
  public Double getPrice() {
      return price;
  }
  
  public Double getPromoPrice() {
      return promoPrice;
  }
  
  public Boolean getInPromo() {
      return inPromo;
  }
  
  public Temporal.Date getExpirationDate() {
      return expirationDate;
  }
  
  public Temporal.Date getPurchaseDate() {
      return purchaseDate;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Product(String id, String code, Integer lot, String name, Double price, Double promoPrice, Boolean inPromo, Temporal.Date expirationDate, Temporal.Date purchaseDate) {
    this.id = id;
    this.code = code;
    this.lot = lot;
    this.name = name;
    this.price = price;
    this.promoPrice = promoPrice;
    this.inPromo = inPromo;
    this.expirationDate = expirationDate;
    this.purchaseDate = purchaseDate;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Product product = (Product) obj;
      return ObjectsCompat.equals(getId(), product.getId()) &&
              ObjectsCompat.equals(getCode(), product.getCode()) &&
              ObjectsCompat.equals(getLot(), product.getLot()) &&
              ObjectsCompat.equals(getName(), product.getName()) &&
              ObjectsCompat.equals(getPrice(), product.getPrice()) &&
              ObjectsCompat.equals(getPromoPrice(), product.getPromoPrice()) &&
              ObjectsCompat.equals(getInPromo(), product.getInPromo()) &&
              ObjectsCompat.equals(getExpirationDate(), product.getExpirationDate()) &&
              ObjectsCompat.equals(getPurchaseDate(), product.getPurchaseDate()) &&
              ObjectsCompat.equals(getCreatedAt(), product.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), product.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCode())
      .append(getLot())
      .append(getName())
      .append(getPrice())
      .append(getPromoPrice())
      .append(getInPromo())
      .append(getExpirationDate())
      .append(getPurchaseDate())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Product {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("code=" + String.valueOf(getCode()) + ", ")
      .append("lot=" + String.valueOf(getLot()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("price=" + String.valueOf(getPrice()) + ", ")
      .append("promoPrice=" + String.valueOf(getPromoPrice()) + ", ")
      .append("inPromo=" + String.valueOf(getInPromo()) + ", ")
      .append("expirationDate=" + String.valueOf(getExpirationDate()) + ", ")
      .append("purchaseDate=" + String.valueOf(getPurchaseDate()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static CodeStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Product justId(String id) {
    return new Product(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      code,
      lot,
      name,
      price,
      promoPrice,
      inPromo,
      expirationDate,
      purchaseDate);
  }
  public interface CodeStep {
    LotStep code(String code);
  }
  

  public interface LotStep {
    NameStep lot(Integer lot);
  }
  

  public interface NameStep {
    PriceStep name(String name);
  }
  

  public interface PriceStep {
    ExpirationDateStep price(Double price);
  }
  

  public interface ExpirationDateStep {
    PurchaseDateStep expirationDate(Temporal.Date expirationDate);
  }
  

  public interface PurchaseDateStep {
    BuildStep purchaseDate(Temporal.Date purchaseDate);
  }
  

  public interface BuildStep {
    Product build();
    BuildStep id(String id);
    BuildStep promoPrice(Double promoPrice);
    BuildStep inPromo(Boolean inPromo);
  }
  

  public static class Builder implements CodeStep, LotStep, NameStep, PriceStep, ExpirationDateStep, PurchaseDateStep, BuildStep {
    private String id;
    private String code;
    private Integer lot;
    private String name;
    private Double price;
    private Temporal.Date expirationDate;
    private Temporal.Date purchaseDate;
    private Double promoPrice;
    private Boolean inPromo;
    @Override
     public Product build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Product(
          id,
          code,
          lot,
          name,
          price,
          promoPrice,
          inPromo,
          expirationDate,
          purchaseDate);
    }
    
    @Override
     public LotStep code(String code) {
        Objects.requireNonNull(code);
        this.code = code;
        return this;
    }
    
    @Override
     public NameStep lot(Integer lot) {
        Objects.requireNonNull(lot);
        this.lot = lot;
        return this;
    }
    
    @Override
     public PriceStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public ExpirationDateStep price(Double price) {
        Objects.requireNonNull(price);
        this.price = price;
        return this;
    }
    
    @Override
     public PurchaseDateStep expirationDate(Temporal.Date expirationDate) {
        Objects.requireNonNull(expirationDate);
        this.expirationDate = expirationDate;
        return this;
    }
    
    @Override
     public BuildStep purchaseDate(Temporal.Date purchaseDate) {
        Objects.requireNonNull(purchaseDate);
        this.purchaseDate = purchaseDate;
        return this;
    }
    
    @Override
     public BuildStep promoPrice(Double promoPrice) {
        this.promoPrice = promoPrice;
        return this;
    }
    
    @Override
     public BuildStep inPromo(Boolean inPromo) {
        this.inPromo = inPromo;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String code, Integer lot, String name, Double price, Double promoPrice, Boolean inPromo, Temporal.Date expirationDate, Temporal.Date purchaseDate) {
      super.id(id);
      super.code(code)
        .lot(lot)
        .name(name)
        .price(price)
        .expirationDate(expirationDate)
        .purchaseDate(purchaseDate)
        .promoPrice(promoPrice)
        .inPromo(inPromo);
    }
    
    @Override
     public CopyOfBuilder code(String code) {
      return (CopyOfBuilder) super.code(code);
    }
    
    @Override
     public CopyOfBuilder lot(Integer lot) {
      return (CopyOfBuilder) super.lot(lot);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder price(Double price) {
      return (CopyOfBuilder) super.price(price);
    }
    
    @Override
     public CopyOfBuilder expirationDate(Temporal.Date expirationDate) {
      return (CopyOfBuilder) super.expirationDate(expirationDate);
    }
    
    @Override
     public CopyOfBuilder purchaseDate(Temporal.Date purchaseDate) {
      return (CopyOfBuilder) super.purchaseDate(purchaseDate);
    }
    
    @Override
     public CopyOfBuilder promoPrice(Double promoPrice) {
      return (CopyOfBuilder) super.promoPrice(promoPrice);
    }
    
    @Override
     public CopyOfBuilder inPromo(Boolean inPromo) {
      return (CopyOfBuilder) super.inPromo(inPromo);
    }
  }
  
}
