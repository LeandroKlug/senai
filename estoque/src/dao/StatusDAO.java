package dao;

import models.Pessoa;
import models.StatusDoItem;

public class StatusDAO  extends DAOModel<StatusDoItem> {

	public boolean gravar(StatusDoItem s1) {
	
		if(s1.getId() > 0) {
		// já está no banco de dados
			this.update(s1);
		} else {
			this.insert(s1);
		}
		return true;
	}	
}
