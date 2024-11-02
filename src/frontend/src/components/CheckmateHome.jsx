import React from 'react'
import { getTranslation } from './LanguageProvider';

const CheckmateHome = () => {
    return (
        <div className='text-center'>
            <h1>{getTranslation("CheckmateHomeH1")}</h1>
            <p>{getTranslation("CheckmateHomeP1")}</p>
            <p>{getTranslation("CheckmateHomeP2")}</p>
            <p>{getTranslation("CheckmateHomeP3")}</p>
            <p>{getTranslation("CheckmateHomeP4")}</p>
        </div>
    )
}

export default CheckmateHome