// ThemeProvider.jsx
import React, { createContext, useState, useContext } from 'react';

// Create a context for theme
const ThemeContext = createContext();

export const ThemeProvider = ({ children }) => {
    const [theme, setTheme] = useState('light');

    // Styles for different themes
    const themes = {
        light: {
            backgroundColor: '#ffffff',
            color: '#000000'
        },
        dark: {
            backgroundColor: '#333333',
            color: '#ffffff'
        },
        solarized: {
            backgroundColor: '#fdf6e3',
            color: '#586e75'
        }
    };

    const currentStyles = themes[theme];

    return (
        <ThemeContext.Provider value={{ theme, setTheme, currentStyles }}>
            {children}
        </ThemeContext.Provider>
    );
};

// Custom hook to use the Theme context
export const useTheme = () => {
    return useContext(ThemeContext);
};
