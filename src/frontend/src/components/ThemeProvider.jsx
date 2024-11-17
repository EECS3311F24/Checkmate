// ThemeProvider.jsx
import React, { createContext, useState, useContext, useEffect } from 'react';

// Create a context for theme
const ThemeContext = createContext();

export const ThemeProvider = ({ children }) => {
    const [theme, setTheme] = useState(() => {
        const savedTheme = localStorage.getItem('theme');
        return savedTheme ? savedTheme : 'light';
    });

    useEffect(() => {
        localStorage.setItem('theme', theme);
    }, [theme]);

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
