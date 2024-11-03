import React from 'react'
import { getTranslation, useLanguage } from './LanguageProvider';

const CheckmateHome = () => {
    const { language, setLanguage } = useLanguage();
    return (
        <div className='text-center'>
            <h1>{getTranslation("CheckmateHomeH1", language)}</h1>
            <p>{getTranslation("CheckmateHomeP1", language)}</p>
            <p>{getTranslation("CheckmateHomeP2", language)}</p>
            <p>{getTranslation("CheckmateHomeP3", language)}</p>
            <p>{getTranslation("CheckmateHomeP4", language)}</p>
        </div>
    )
}

export default CheckmateHome