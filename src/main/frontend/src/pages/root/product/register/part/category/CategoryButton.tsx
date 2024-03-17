import React, {useEffect, useRef, useState} from "react";


export interface CategoryButtonProps{
    category: string[]
    callback: (result: string, level: number) => void;
    level: number;
}
function CategoryButton(props:CategoryButtonProps) {
    const [title, setTitle] = useState<string> ("카테고리");
    const [dropdown, setDropdown] = useState (false);


    const dropdownRef = useRef<HTMLDivElement>(null);
    useEffect(() => {
        const handleClickOutside = (event :any) => {
            if (!dropdownRef.current || dropdownRef.current.contains(event.target)) {
                return;
            }
            setDropdown(false);
        };

        document.addEventListener('mousedown', handleClickOutside);

        return () => document.removeEventListener('mousedown', handleClickOutside);
    }, [dropdown, dropdownRef]);




    const toggleDropdown = () => {
        setDropdown(!dropdown);
    }

    const handleOption = (result : number) => {
        console.log(props.category)
        if (props.category){
            setTitle(props.category[result]);
            props.callback(props.category[result], props.level);
        }
        toggleDropdown();

    }
    const generateOptions = () => {
        const options: any[] = [];
        props.category?.forEach((item, index) => {
            options.push(
                <button
                    key={index}
                    onClick={() => handleOption(index)}
                    className="block px-4 py-2 hover:bg-gray-100  dark:hover:text-black "
                >
                    {item}
                </button>
            );
        });
        return options;
    }

    interface DivStyles {
        color: string;
        top: string;
        left: string;
        position: any;
    }
    const DropDownStyles : (index : number) => DivStyles = (index) => {
        const columns = 3;
        const columnIndex = index % columns;
        const top = 90;
        const left = 10 + (index-1) * 270;

        return {
            color: "white",
            top: `${top}px`,
            left: `${left}px`,
            position: "absolute",
        }
    };

    return (
        <div>
            <button id="dropdownHoverButton"
                    onClick={toggleDropdown}
                    className="text-white bg-green-700 hover:bg-gray-800 focus:ring-4 focus:outline-none focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-gray-600 dark:hover:bg-gray-700 dark:focus:ring-gray-800"
                    type="button">{title}
                <svg className="w-2.5 h-2.5 ms-3" aria-hidden="true"
                     xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
                    <path stroke="currentColor"
                          d="m1 1 4 4 4-4"/>
                </svg>
            </button>
            {dropdown && (
                <div ref={dropdownRef}
                     style={DropDownStyles(props.level)}
                    className="top-full left-0 mt-1 bg-green-400 rounded-lg shadow w-70 dark:bg-gray-700">
                    {generateOptions()}
                </div>
            )}
        </div>

    )
}

export default CategoryButton;