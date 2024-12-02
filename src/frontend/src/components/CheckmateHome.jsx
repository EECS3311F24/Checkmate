import React from 'react';
import { getTranslation, useLanguage } from './LanguageProvider';
import { useTheme } from './ThemeProvider';

const CheckmateHome = () => {
    const { language } = useLanguage();
    const { theme } = useTheme();

    // Apply different styles for header and paragraph based on the current theme
    const style = theme === 'dark' ? { color: '#ffffff' } : theme === 'solarized' ? { color: '#add8e6' } : { color: '#000000' };

    return (
        <div className='text-center'>
            <h1 style={style}>{getTranslation("CheckmateHomeH1", language)}</h1>
            <p style={style}>{getTranslation("CheckmateHomeP1", language)}</p>
            <p style={style}>{getTranslation("CheckmateHomeP2", language)}</p>
            <p style={style}>{getTranslation("CheckmateHomeP3", language)}</p>
            <p style={style}>{getTranslation("CheckmateHomeP4", language)}</p>
        </div>
    );
};

export default CheckmateHome;
