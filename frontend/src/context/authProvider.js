import React from 'react';

export const AuthContext = React.createContext(null);

export const AuthProvider = ({ userData, children }) => {
  let [user, setUser] = React.useState(userData);

  return (
    <AuthContext.Provider value={{ user, setUser }}>
      {children}
    </AuthContext.Provider>
  );
};
