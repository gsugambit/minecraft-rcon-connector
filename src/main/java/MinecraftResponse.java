import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MinecraftResponse {

	private int length;
	private int requestId;
	private int type;
	private String payload;
	
	public MinecraftResponse(byte[] response) {
		ByteBuffer buffer = ByteBuffer.wrap(response);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		length = buffer.getInt(0);
		requestId = buffer.getInt(4);
		type = buffer.getInt(8);
		
		byte[] payloadBytes = new byte[response.length - 13];
		for(int i = 12; i < response.length -1; i++) {
			payloadBytes[i-12] = response[i];
		}
		
		payload = new String(payloadBytes);
	}
	
	public String toString() {
		StringBuilder builder=  new StringBuilder();
		builder.append("length: " + length);
		builder.append(" requestId: " + requestId);
		builder.append(" type: " + type);
		builder.append(" payload: " +  payload);
		
		return builder.toString();
	}
}
