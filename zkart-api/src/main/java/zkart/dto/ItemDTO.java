package zkart.dto;

import java.util.ArrayList;

import zkart.entity.Item;

public class ItemDTO {
	Item item;
	ArrayList<Pair> attribures;
	ArrayList<Pair> filterValues;
	public ItemDTO(Item item, ArrayList<Pair> attribures, ArrayList<Pair> filterValues) {
		this.item = item;
		this.attribures = attribures;
		this.filterValues = filterValues;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public ArrayList<Pair> getAttribures() {
		return attribures;
	}
	public void setAttribures(ArrayList<Pair> attribures) {
		this.attribures = attribures;
	}
	public ArrayList<Pair> getFilterValues() {
		return filterValues;
	}
	public void setFilterValues(ArrayList<Pair> filterValues) {
		this.filterValues = filterValues;
	}
	@Override
	public String toString() {
		return "ItemDTO [item=" + item + ", attribures=" + attribures + ", filterValues=" + filterValues + "]";
	}
}