package io.sarl.ue4.influence;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import io.sarl.ue4.Vector3D;
import io.sarl.ue4.influence.Influence;
import io.sarl.ue4.influence.SendInfluence;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Allows sending influences through UDP protocol.
 * @author Alexandre Lombard
 */
@SarlSpecification("0.8")
@SarlElementType(21)
@SuppressWarnings("all")
public class UdpSendInfluence extends Skill implements SendInfluence {
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
   * The UDP port (currently hard-coded)
   */
  private final int port = 4242;
  
  public void install() {
  }
  
  public void uninstall() {
  }
  
  public void sendInfluence(final Vector3D force) {
    try {
      UUID _iD = this.getOwner().getID();
      final Influence influence = new Influence(force, _iD);
      final byte[] buffer = influence.toByteArray();
      int _length = buffer.length;
      final DatagramPacket packet = new DatagramPacket(buffer, _length, this.ipAddress, this.port);
      final DatagramSocket socket = new DatagramSocket();
      socket.send(packet);
      socket.close();
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
    UdpSendInfluence other = (UdpSendInfluence) obj;
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
  public UdpSendInfluence() {
    super();
  }
  
  @SyntheticMember
  public UdpSendInfluence(final Agent agent) {
    super(agent);
  }
}
