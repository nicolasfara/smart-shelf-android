package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasOne;
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

/** This is an auto generated class representing the ProductShelf type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ProductShelves")
public final class ProductShelf implements Model {
  public static final QueryField ID = field("ProductShelf", "id");
  public static final QueryField SHELF_ID = field("ProductShelf", "shelfId");
  public static final QueryField QUANTITY = field("ProductShelf", "quantity");
  public static final QueryField PRODUCT_SHELF_PRODUCT_ID = field("ProductShelf", "productShelfProductId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Int", isRequired = true) Integer shelfId;
  private final @ModelField(targetType="Product", isRequired = true) @HasOne(associatedWith = "id", type = Product.class) Product product = null;
  private final @ModelField(targetType="Int", isRequired = true) Integer quantity;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID", isRequired = true) String productShelfProductId;
  public String getId() {
      return id;
  }
  
  public Integer getShelfId() {
      return shelfId;
  }
  
  public Product getProduct() {
      return product;
  }
  
  public Integer getQuantity() {
      return quantity;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getProductShelfProductId() {
      return productShelfProductId;
  }
  
  private ProductShelf(String id, Integer shelfId, Integer quantity, String productShelfProductId) {
    this.id = id;
    this.shelfId = shelfId;
    this.quantity = quantity;
    this.productShelfProductId = productShelfProductId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ProductShelf productShelf = (ProductShelf) obj;
      return ObjectsCompat.equals(getId(), productShelf.getId()) &&
              ObjectsCompat.equals(getShelfId(), productShelf.getShelfId()) &&
              ObjectsCompat.equals(getQuantity(), productShelf.getQuantity()) &&
              ObjectsCompat.equals(getCreatedAt(), productShelf.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), productShelf.getUpdatedAt()) &&
              ObjectsCompat.equals(getProductShelfProductId(), productShelf.getProductShelfProductId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getShelfId())
      .append(getQuantity())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getProductShelfProductId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ProductShelf {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("shelfId=" + String.valueOf(getShelfId()) + ", ")
      .append("quantity=" + String.valueOf(getQuantity()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("productShelfProductId=" + String.valueOf(getProductShelfProductId()))
      .append("}")
      .toString();
  }
  
  public static ShelfIdStep builder() {
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
  public static ProductShelf justId(String id) {
    return new ProductShelf(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      shelfId,
      quantity,
      productShelfProductId);
  }
  public interface ShelfIdStep {
    QuantityStep shelfId(Integer shelfId);
  }
  

  public interface QuantityStep {
    ProductShelfProductIdStep quantity(Integer quantity);
  }
  

  public interface ProductShelfProductIdStep {
    BuildStep productShelfProductId(String productShelfProductId);
  }
  

  public interface BuildStep {
    ProductShelf build();
    BuildStep id(String id);
  }
  

  public static class Builder implements ShelfIdStep, QuantityStep, ProductShelfProductIdStep, BuildStep {
    private String id;
    private Integer shelfId;
    private Integer quantity;
    private String productShelfProductId;
    @Override
     public ProductShelf build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ProductShelf(
          id,
          shelfId,
          quantity,
          productShelfProductId);
    }
    
    @Override
     public QuantityStep shelfId(Integer shelfId) {
        Objects.requireNonNull(shelfId);
        this.shelfId = shelfId;
        return this;
    }
    
    @Override
     public ProductShelfProductIdStep quantity(Integer quantity) {
        Objects.requireNonNull(quantity);
        this.quantity = quantity;
        return this;
    }
    
    @Override
     public BuildStep productShelfProductId(String productShelfProductId) {
        Objects.requireNonNull(productShelfProductId);
        this.productShelfProductId = productShelfProductId;
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
    private CopyOfBuilder(String id, Integer shelfId, Integer quantity, String productShelfProductId) {
      super.id(id);
      super.shelfId(shelfId)
        .quantity(quantity)
        .productShelfProductId(productShelfProductId);
    }
    
    @Override
     public CopyOfBuilder shelfId(Integer shelfId) {
      return (CopyOfBuilder) super.shelfId(shelfId);
    }
    
    @Override
     public CopyOfBuilder quantity(Integer quantity) {
      return (CopyOfBuilder) super.quantity(quantity);
    }
    
    @Override
     public CopyOfBuilder productShelfProductId(String productShelfProductId) {
      return (CopyOfBuilder) super.productShelfProductId(productShelfProductId);
    }
  }
  
}
