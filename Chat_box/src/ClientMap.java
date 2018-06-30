import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//通过API了解到HashMap类，继承HashMap类，通过用户昵称到其对应输出流的映射，实现私聊，群聊
public class ClientMap<K, V> extends HashMap<K, V> {
	
	
	  //根据Value删除指定项
      public void removeByValue(Object value) {
    	  for(Object key:keySet()) {
    		  if(get(key)==value||get(key).equals(value)) {
    			  remove(key);
    			  break;
    		  }
    	  }
      }
      
      //获取Value列表
      public Set<V> ValueSet(){
    	  Set<V> valueList=new HashSet<V>();
    	  for(Object key:keySet()) {
    		  valueList.add(get(key));
    	  }
    	  return valueList;
      }
      
      //重写put方法
      public V put(K key,V value) {
    	  for (V val:ValueSet()) {
    		  if(val==value||val.equals(value)) {
    			  throw new RuntimeException("value重复！");
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
