package com.umurcanemre.services.metadata.domain;

import lombok.Data;

@Data
public class Language {
	private String code;
	private String name;
	private String formattedName;
	
	@Override
	public String toString() {
		return code + ":" + formattedName;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if( !(o instanceof Language)) // instanceof returns false for null
			return false;
		
		Language oL = (Language)o;
		
		//All fields equal
		if(this.code.equals(oL.getCode()) && 
				this.name.equals(oL.getName()) && 
				this.formattedName.equals(oL.getFormattedName()))
			return true;
		
		//If not equal, all fields should be different
		if(this.code.equals(oL.getCode()) || 
				this.name.equals(oL.getName()) || 
				this.formattedName.equals(oL.getFormattedName()))
			throw new IllegalStateException("Language object is not unique\n"+this.toString()+"\n"+oL.toString());
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return (code.hashCode()*29) + (name.hashCode()*19);
	}
}
