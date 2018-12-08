package io.sarl.ue4.influence;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import io.sarl.ue4.Vector3D;
import io.sarl.ue4.influence.Influence;
import io.sarl.ue4.influence.SendInfluence;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Allows sending influences by pushing them in a shared memory segment (a memory mapped file).
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(21)
@SuppressWarnings("all")
public class MemoryMappedFileSendInfluence extends Skill implements SendInfluence {
  /**
   * The name of the memory mapped file (currently hard-coded)
   */
  private final String fileName = "SHM_SARL_UE4";
  
  private FileChannel channel = null;
  
  private MappedByteBuffer mappedByteBuffer = null;
  
  public void install() {
    try {
      synchronized (this) {
        this.channel = FileChannel.open(
          new File(this.fileName).toPath(), 
          StandardOpenOption.READ, 
          StandardOpenOption.WRITE, 
          StandardOpenOption.CREATE);
        this.mappedByteBuffer = this.channel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void uninstall() {
    try {
      synchronized (this) {
        this.channel.close();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void sendInfluence(final Vector3D force) {
    UUID _iD = this.getOwner().getID();
    final Influence influence = new Influence(force, _iD);
    final byte[] buffer = influence.toByteArray();
    synchronized (this) {
      this.mappedByteBuffer.put(buffer);
    }
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
    MemoryMappedFileSendInfluence other = (MemoryMappedFileSendInfluence) obj;
    if (!Objects.equals(this.fileName, other.fileName)) {
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
    result = prime * result + Objects.hashCode(this.fileName);
    return result;
  }
  
  @SyntheticMember
  public MemoryMappedFileSendInfluence() {
    super();
  }
  
  @SyntheticMember
  public MemoryMappedFileSendInfluence(final Agent agent) {
    super(agent);
  }
}
