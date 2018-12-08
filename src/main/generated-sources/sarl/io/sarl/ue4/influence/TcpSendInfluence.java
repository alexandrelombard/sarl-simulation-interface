package io.sarl.ue4.influence;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import io.sarl.ue4.Vector3D;
import io.sarl.ue4.influence.Influence;
import io.sarl.ue4.influence.SendInfluence;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Allows sending influences through TCP protocol.
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(21)
@SuppressWarnings("all")
public class TcpSendInfluence extends Skill implements SendInfluence {
  /**
   * The IP address (currently hard-coded)
   */
  private final InetAddress ipAddress = new Function0<InetAddress>() {
    public InetAddress apply() {
      try {
        InetAddress _byName = InetAddress.getByName("127.0.0.1");
        return _byName;
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
  }.apply();
  
  /**
   * The TCP port (currently hard-coded)
   */
  private final int port = 4242;
  
  private Socket socket;
  
  public void install() {
    try {
      synchronized (this) {
        Socket _socket = new Socket(this.ipAddress, this.port);
        this.socket = _socket;
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void uninstall() {
    try {
      synchronized (this) {
        this.socket.close();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void sendInfluence(final Vector3D force) {
    try {
      UUID _iD = this.getOwner().getID();
      final Influence influence = new Influence(force, _iD);
      final byte[] buffer = influence.toByteArray();
      synchronized (this) {
        this.socket.getOutputStream().write(buffer);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
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
    TcpSendInfluence other = (TcpSendInfluence) obj;
    if (other.port != this.port)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.port;
    return result;
  }
  
  @SyntheticMember
  public TcpSendInfluence() {
    super();
  }
  
  @SyntheticMember
  public TcpSendInfluence(final Agent agent) {
    super(agent);
  }
}
