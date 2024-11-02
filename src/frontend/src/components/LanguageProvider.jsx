import en from '../translations/en.json';
import es from '../translations/es.json';
import fn from '../translations/fn.json';

const translations = {
  en,
  es,
  fn
};

// Function to get translated strings based on language
export const getTranslation = (key, currentLanguage = 'en') => {
  return translations[currentLanguage][key] || key;
};
