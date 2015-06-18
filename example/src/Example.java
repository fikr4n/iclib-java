import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Example {

	protected void run() throws ReflectiveOperationException, IllegalArgumentException {
		Method[] methods = this.getClass().getDeclaredMethods();
		Arrays.sort(methods, new Comparator<Method>() {
	
			@Override
			public int compare(Method o1, Method o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Method m : methods) {
			if (m.getModifiers() != Modifier.PUBLIC) continue;
			if (m.getParameterTypes().length != 0 || m.getReturnType() != void.class) continue;
			
			print("----- " + m.getName() + " -----");
			m.invoke(this);
			print("");
		}
	}

	protected void print(Object x) {
		System.out.println(x);
	}

}
