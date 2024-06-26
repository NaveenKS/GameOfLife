package com.logical.gameoflife.models;

import java.util.Objects;

public class Position {
	  private int row;
	  private int column;

	  public Position(int row, int column){
			this.row = row;
			this.column = column;
	  }

	  public int getRow() {
			return row;
	  }

	  public int getColumn() {
			return column;
	  }

	  @Override
	  public int hashCode(){
			return Objects.hash(row,column);
	  }

	  @Override
	  public boolean equals(Object o){
			if(this == o)
				  return true;

			if(o==null)
				  return false;

			if(this.getClass()!=o.getClass())
				  return false;

			Position position = (Position) o;

			return (this.row == position.getRow() && this.getColumn() == position.getColumn());
	  }
}

