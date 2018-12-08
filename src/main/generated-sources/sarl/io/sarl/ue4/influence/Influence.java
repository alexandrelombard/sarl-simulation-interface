package io.sarl.ue4.influence;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.ue4.Vector3D;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Represents an influence (action on the world)
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Influence {
  public final Vector3D influence;
  
  public final UUID id;
  
  public Influence(final Vector3D influence, final UUID id) {
    this.influence = influence;
    this.id = id;
  }
  
  /**
   * Custom serialization
   */
  @Pure
  public byte[] toByteArray() {
    final String idStr = this.id.toString();
    int _length = idStr.length();
    int _plus = (((3 * 8) + 4) + _length);
    final ByteBuffer byteBuffer = ByteBuffer.allocate(_plus);
    byteBuffer.putDouble((this.influence.x).doubleValue());
    byteBuffer.putDouble((this.influence.y).doubleValue());
    byteBuffer.putDouble((this.influence.z).doubleValue());
    byteBuffer.putInt(idStr.length());
    byteBuffer.put(idStr.getBytes());
    return byteBuffer.array();
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
    Influence other = (Influence) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.id);
    return result;
  }
}
