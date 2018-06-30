import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//ͨ��API�˽⵽HashMap�࣬�̳�HashMap�࣬ͨ���û��ǳƵ����Ӧ�������ӳ�䣬ʵ��˽�ģ�Ⱥ��
public class ClientMap<K, V> extends HashMap<K, V> {
	
	
	  //����Valueɾ��ָ����
      public void removeByValue(Object value) {
    	  for(Object key:keySet()) {
    		  if(get(key)==value||get(key).equals(value)) {
    			  remove(key);
    			  break;
    		  }
    	  }
      }
      
      //��ȡValue�б�
      public Set<V> ValueSet(){
    	  Set<V> valueList=new HashSet<V>();
    	  for(Object key:keySet()) {
    		  valueList.add(get(key));
    	  }
    	  return valueList;
      }
      
      //��дput����
      public V put(K key,V value) {
    	  for (V val:ValueSet()) {
    		  if(val==value||val.equals(value)) {
    			  throw new RuntimeException("value�ظ���");
    		  }
    	  }
    	  return super.put(key, value);
      }
      
      public K getKeyByvalue(Object value) {
    	  for(K key:keySet()) {
    		  if(get(key)==value||get(key).equals(value)) {
    			  return key;
    		  }
    	  }return null;
      }
}
