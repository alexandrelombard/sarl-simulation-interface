package io.sarl.ue4;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.DoubleExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Immutable 3D vector
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Vector3D {
  public final Double x;
  
  public final Double y;
  
  public final Double z;
  
  public Vector3D(final Double x, final Double y, final Double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  @Pure
  public double distance(final Vector3D v) {
    return Math.sqrt(this.distanceSquared(v));
  }
  
  @Pure
  public double distanceSquared(final Vector3D v) {
    double _minus = DoubleExtensions.operator_minus(this.x, v.x);
    double _pow = Math.pow(_minus, 2d);
    double _minus_1 = DoubleExtensions.operator_minus(this.y, v.y);
    double _pow_1 = Math.pow(_minus_1, 2d);
    double _plus = (_pow + _pow_1);
    double _minus_2 = DoubleExtensions.operator_minus(this.z, v.z);
    double _pow_2 = Math.pow(_minus_2, 2);
    return (_plus + _pow_2);
  }
  
  @Pure
  public Vector3D operator_plus(final Vector3D v1) {
    double _plus = DoubleExtensions.operator_plus(v1.x, this.x);
    double _plus_1 = DoubleExtensions.operator_plus(v1.y, this.y);
    double _plus_2 = DoubleExtensions.operator_plus(v1.z, this.z);
    return new Vector3D(Double.valueOf(_plus), Double.valueOf(_plus_1), Double.valueOf(_plus_2));
  }
  
  @Pure
  public Vector3D operator_minus(final Vector3D v1) {
    double _minus = DoubleExtensions.operator_minus(this.x, v1.x);
    double _minus_1 = DoubleExtensions.operator_minus(this.y, v1.y);
    double _minus_2 = DoubleExtensions.operator_minus(this.z, v1.z);
    return new Vector3D(Double.valueOf(_minus), Double.valueOf(_minus_1), Double.valueOf(_minus_2));
  }
  
  @Pure
  public Vector3D operator_multiply(final Double l) {
    double _multiply = DoubleExtensions.operator_multiply(this.x, l);
    double _multiply_1 = DoubleExtensions.operator_multiply(this.y, l);
    double _multiply_2 = DoubleExtensions.operator_multiply(this.z, l);
    return new Vector3D(Double.valueOf(_multiply), Double.valueOf(_multiply_1), Double.valueOf(_multiply_2));
  }
  
  @Pure
  public Vector3D operator_divide(final Double l) {
    double _divide = DoubleExtensions.operator_divide(this.x, l);
    double _divide_1 = DoubleExtensions.operator_divide(this.y, l);
    double _divide_2 = DoubleExtensions.operator_divide(this.z, l);
    return new Vector3D(Double.valueOf(_divide), Double.valueOf(_divide_1), Double.valueOf(_divide_2));
  }
  
  @Pure
  public double length() {
    double _multiply = DoubleExtensions.operator_multiply(this.x, this.x);
    double _multiply_1 = DoubleExtensions.operator_multiply(this.y, this.y);
    double _plus = (_multiply + _multiply_1);
    double _multiply_2 = DoubleExtensions.operator_multiply(this.z, this.z);
    return (_plus + _multiply_2);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Vector3D other = (Vector3D) obj;
    if (Double.doubleToLongBits(other.x) != Double.doubleToLongBits(this.x))
      return false;
    if (Double.doubleToLongBits(other.y) != Double.doubleToLongBits(this.y))
      return false;
    if (Double.doubleToLongBits(other.z) != Double.doubleToLongBits(this.z))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
    return result;
  }
}
