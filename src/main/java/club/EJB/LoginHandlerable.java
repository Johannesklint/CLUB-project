package club.EJB;

import club.DAO.User;

public interface LoginHandlerable {

	void onLogin(User tryLoginUser);

}
