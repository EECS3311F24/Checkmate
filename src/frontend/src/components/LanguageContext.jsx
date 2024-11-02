import React, { createContext, useContext, useState } from 'react';

// Create a Context for language
const LanguageContext = createContext();

// LanguageProvider component to wrap your app and manage language state
export const LanguageProvider = ({ children }) => {
  const [language, setLanguage] = useState('en');

  return (
    <LanguageContext.Provider value={{ language, setLanguage }}>
      {children}
    </LanguageContext.Provider>
  );
};

// Custom hook to use language context
export const useLanguage = () => useContext(LanguageContext);