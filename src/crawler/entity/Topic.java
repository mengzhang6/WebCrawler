package crawler.entity;

import java.io.Serializable;

public class Topic implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4181057508242116682L;
	private String link_id;//话题id
	private String name;//话题名称

	public String getLink_id() {
		return link_id;
	}

	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
