package tour1;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Prerequisites {

	
	private int compare(String a,String b){
		//a.
		Pattern p = Pattern.compile("(\\w{3,4})(\\d{3})");
		Matcher ma = p.matcher(a);
		Matcher mb = p.matcher(b);
		boolean boo = ma.matches() && mb.matches();
		assert boo;
		
		int ia = Integer.parseInt(ma.group(2));
		int ib = Integer.parseInt(mb.group(2));
		if(ia == ib){
			return a.compareTo(b);
		}else {
			return ia - ib;
		}
	}
	
	
	private Node<String> parse(String line){
		Node<String> res = new Node<String>(null);
		String[] s = line.split(":");
		res.data = s[0];
		if(s.length>1){
			String[] pre = s[1].trim().split(" ");
			for(int i=0;i<pre.length;i++){
				Node<String> child = new Node<String>(res);
				child.data = pre[i];
				res.children.add(child);
			}
		}
		
		return res;
	}
	
	public String[] orderClasses(String[] param0){
		List<String> a = new ArrayList<String>();
		
		
		List<Node<String>> ready = new ArrayList<Node<String>>();
		List<Node<String>> wait = new ArrayList<Node<String>>();
		List<Node<String>> done = new ArrayList<Node<String>>();
		
		//init;
		for(int i=0;i<param0.length;i++){
			Node<String> cur = this.parse(param0[i]);
			if(cur.children.size() == 0){
				this.append(ready, cur);
			}else{
				wait.add(cur);
			}
		}
		
		while(wait.size() != 0 || ready.size() != 0){
			
			if(ready.size() == 0){
				break;
			}
			
			Node<String> s = ready.remove(0);
			done.add(s);
			a.add(s.data);
			System.out.println("done:"+ s.data);
			
			List<Node<String>> rm  = new ArrayList<Node<String>>();
			
			for(Node<String> n : wait){
				
				List<Node<String>> rm2  = new ArrayList<Node<String>>();
				for(Node<String> m: n.children){
				//	System.out.println(s.data + m.data);
					if(m.data.equals(s.data)){
						rm2.add(m);
					}
				}
				
				for(Node<String> x: rm2){
					n.children.remove(x);
				}
				
				//System.out.println(n.children.size());
				
				if(n.children.size() == 0){
				//	System.out.println("ready:"+ n.data);
					rm.add(n);
					
					this.append(ready, n);
				}
				
			}
			
			for(Node<String> x: rm){
				wait.remove(x);
			}
		
		
		}
		
		
		
		System.out.println(a.toArray());
		String[] res = new String[a.size()];
		for(int i=0;i<a.size();i++){
			res[i] = a.get(i);
		}
		
		return res;
		//return a.toArray();
	}
	
	private void append(List l , Node<String> o){
		if(l.size() ==0){
			l.add(o);
		}else{
			boolean did = false;
			for(int j=0;j<l.size();j++){
				int c = this.compare(o.data,((Node<String>)(l.get(j))).data);
				if( c < 0){
					l.add(j, o);
					did = true;
					break;
				}
				if(c == 0){
					did = true;
					break;
				}
			}
			if(!did){
				l.add(o);
			}
		}
	}

	
	public class Node<T>{
		public Node<T> parent; 
		public List<Node<T>> children;
		public T data;
		
		public Node(Node<T> parent){
			this.parent = parent;
			children = new ArrayList<Node<T>>(); 
		}
	}
	
	
	public static void main(String[] args){
		//System.out.println(compare("abba123","aca123"));
		String[] s = {
				"CSE121: CSE110",
				"CSE110:",
				"MATH122:"
				};
		
		String[] s2 = {
				"CSE258: CSE244 CSE243 INTR100",
				"CSE221: CSE254 INTR100",
				"CSE254: CSE111 MATH210 INTR100",
				"CSE244: CSE243 MATH210 INTR100",
				"MATH210: INTR100",
				"CSE101: INTR100",
				"CSE111: INTR100",
				"ECE201: CSE111 INTR100",
				"ECE111: INTR100",
				"CSE243: CSE254",
				"INTR100:"
				};
		String[] s3 = {"CSC200: MAT100"};
		new Prerequisites().orderClasses(s3);
		
	
	}
}

