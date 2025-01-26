package com.coderhouse.dto;

import java.util.List;

public class productosEnFacturaDTO {
	
	private Long codeFac;
	
	private List<Long> productIds;
	
	public Long getCodeFac() {
		return codeFac;
	}

	public void setCodeFac(Long codeFac) {
		this.codeFac = codeFac;
	}

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

}
