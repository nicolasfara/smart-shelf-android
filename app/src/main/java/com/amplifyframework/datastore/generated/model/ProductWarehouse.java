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

/** This is an auto generated class representing the ProductWarehouse type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ProductWarehouses")
public final class ProductWarehouse implements Model {
  public static final QueryField ID = field("ProductWarehouse", "id");
  public static final QueryField QUANTITY = field("ProductWarehouse", "quantity");
  public static final QueryField PRODUCT_WAREHOUSE_PRODUCT_ID = field("ProductWarehouse", "productWarehouseProductId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Product", isRequired = true) @HasOne(associatedWith = "id", type = Product.class) Product product = null;
  private final @ModelField(targetType="Int", isRequired = true) Integer quantity;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID", isRequired = true) String productWarehouseProductId;
  public String getId() {
      return id;
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
  
  public String getProductWarehouseProductId() {
      return productWarehouseProductId;
  }
  
  private ProductWarehouse(String id, Integer quantity, String productWarehouseProductId) {
    this.id = id;
    this.quantity = quantity;
    this.productWarehouseProductId = productWarehouseProductId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ProductWarehouse productWarehouse = (ProductWarehouse) obj;
      return ObjectsCompat.equals(getId(), productWarehouse.getId()) &&
              ObjectsCompat.equals(getQuantity(), productWarehouse.getQuantity()) &&
              ObjectsCompat.equals(getCreatedAt(), productWarehouse.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), productWarehouse.getUpdatedAt()) &&
              ObjectsCompat.equals(getProductWarehouseProductId(), productWarehouse.getProductWarehouseProductId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getQuantity())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getProductWarehouseProductId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ProductWarehouse {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("quantity=" + String.valueOf(getQuantity()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("productWarehouseProductId=" + String.valueOf(getProductWarehouseProductId()))
      .append("}")
      .toString();
  }
  
  public static QuantityStep builder() {
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
  public static ProductWarehouse justId(String id) {
    return new ProductWarehouse(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      quantity,
      productWarehouseProductId);
  }
  public interface QuantityStep {
    ProductWarehouseProductIdStep quantity(Integer quantity);
  }
  

  public interface ProductWarehouseProductIdStep {
    BuildStep productWarehouseProductId(String productWarehouseProductId);
  }
  

  public interface BuildStep {
    ProductWarehouse build();
    BuildStep id(String id);
  }
  

  public static class Builder implements QuantityStep, ProductWarehouseProductIdStep, BuildStep {
    private String id;
    private Integer quantity;
    private String productWarehouseProductId;
    @Override
     public ProductWarehouse build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ProductWarehouse(
          id,
          quantity,
          productWarehouseProductId);
    }
    
    @Override
     public ProductWarehouseProductIdStep quantity(Integer quantity) {
        Objects.requireNonNull(quantity);
        this.quantity = quantity;
        return this;
    }
    
    @Override
     public BuildStep productWarehouseProductId(String productWarehouseProductId) {
        Objects.requireNonNull(productWarehouseProductId);
        this.productWarehouseProductId = productWarehouseProductId;
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
    private CopyOfBuilder(String id, Integer quantity, String productWarehouseProductId) {
      super.id(id);
      super.quantity(quantity)
        .productWarehouseProductId(productWarehouseProductId);
    }
    
    @Override
     public CopyOfBuilder quantity(Integer quantity) {
      return (CopyOfBuilder) super.quantity(quantity);
    }
    
    @Override
     public CopyOfBuilder productWarehouseProductId(String productWarehouseProductId) {
      return (CopyOfBuilder) super.productWarehouseProductId(productWarehouseProductId);
    }
  }
  
}
