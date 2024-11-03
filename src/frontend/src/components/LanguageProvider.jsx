import React, { createContext, useContext, useState } from 'react';
import en from '../translations/en.json';
import es from '../translations/es.json';
import fn from '../translations/fn.json';

const translations = {
  en,
  es,
  fn
};

export const getTranslation = (key, currentLanguage = 'en') => {
  if (!translations[currentLanguage]) {
    console.error(`Language "${currentLanguage}" is not available, defaulting to 'en'`);
    currentLanguage = 'en';
  }
  return translations[currentLanguage][key] || key;
};

const LanguageContext = createContext();

export const LanguageProvider = ({ children }) => {
  const [language, setLanguage] = useState('en');

  return (
    <LanguageContext.Provider value={{ language, setLanguage }}>
      {children}
    </LanguageContext.Provider>
  );
};

export const useLanguage = () => {
  const context = useContext(LanguageContext);
  if (!context) {
    throw new Error('useLanguage must be used within a LanguageProvider');
  }
  return context;
};
