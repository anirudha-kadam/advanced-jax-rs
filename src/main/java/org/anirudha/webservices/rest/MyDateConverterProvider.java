package org.anirudha.webservices.rest;

import java.lang.annotation.Annotation;

import java.lang.reflect.Type;
import java.util.Calendar;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class MyDateConverterProvider implements ParamConverterProvider {

	public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericeType, Annotation[] annotations) {
		
		if(rawType.getName().equals(MyDate.class.getName())){
			
			return new ParamConverter<T>(){

				public T fromString(String value) {
					
					Calendar requestedDate = Calendar.getInstance();
					
					if("tomorrow".equals(value)){
						requestedDate.add(Calendar.DATE, 1);
					}else if("yesterday".equals(value)){
						requestedDate.add(Calendar.DATE, -1);
					}
					
					MyDate myDate = new MyDate();
					myDate.setDate(requestedDate.get(Calendar.DATE));
					myDate.setMonth(requestedDate.get(Calendar.MONTH) + 1);
					myDate.setYear(requestedDate.get(Calendar.YEAR));
					
					return rawType.cast(myDate);
				}

				public String toString(T myBean) {
					if(myBean == null){
						return null;
					}
					return myBean.toString();
				}
				
			};
		}
		return null;
	}

}
