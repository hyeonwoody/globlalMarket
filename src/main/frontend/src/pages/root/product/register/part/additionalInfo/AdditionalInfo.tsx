import React, {ChangeEvent, useEffect, useState} from 'react';
import AdditionalInfoAPI from "./AdditionalInfoAPI";

interface AdditionalInfoProps{
    fetchData : boolean,
    platform : number,
    category :string[],
    callback : (data : string[]) => void,
}
function AdditionalInfo(props:AdditionalInfoProps) {
    const [additionalInfoList, setAdditionalInfoList] = useState<string[]>([]);
    const [title, setTitle] = useState<string[]>([]);

    useEffect(() => {
        AdditionalInfoAPI(AxiosCallback, props);
    }, [props.category]);

    if (props.fetchData) {
        console.log("추가");
        props.callback(additionalInfoList);
    }

    const AxiosCallback = (data : string[]) => {
        console.log(data);
        setAdditionalInfoList(data);
        setTitle(data);
    }

    const handleInputChange = (index : number) =>
        (event: ChangeEvent<HTMLInputElement> | ChangeEvent<HTMLTextAreaElement>) =>
    {
        console.log(event.target.value);

        setAdditionalInfoList((prevState) => {
            const updatedAdditionalInfoList = [...prevState];
            updatedAdditionalInfoList[index] = event.target.value;
            return updatedAdditionalInfoList;
        });
    }

    return (
        <div className="flex flex-wrap -mx-3 mb-2" id={"product-additionalInfo"}>
            {additionalInfoList?.map((info, index) => (
                <div className="md:w-1/2 px-3 mb-0 md:mb-2" id={"product-price"} key={index}>
                    <label
                        className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                        htmlFor="grid-product-name">
                        {title[index]}
                    </label>
                    <input
                        className="appearance-none block bg-gray-200 text-gray-700 border border-gray-200 rounded py-2 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                        id="grid-last-name"
                        type="text"
                        value={info}
                        placeholder={info}
                        onChange={handleInputChange(index)}/>
                </div>
            ))}

        </div>
    )
}

export default AdditionalInfo;