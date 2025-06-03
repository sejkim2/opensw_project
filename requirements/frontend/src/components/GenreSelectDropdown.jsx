import React, { useEffect, useState } from "react";
import Select from "react-select";

// ✅ 커스텀 체크박스 옵션
const CheckboxOption = (props) => {
    const { data, isSelected, isFocused, innerRef, innerProps } = props;

    return (
        <div
            ref={innerRef}
            {...innerProps}
            style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "flex-start",
                padding: "0px 12px",
                backgroundColor: isFocused ? "#f0f0f0" : "transparent",
                borderRadius: "8px",
                cursor: "pointer",
            }}
        >
            <input
                type="checkbox"
                checked={isSelected}
                readOnly
                style={{
                    appearance: "none",
                    width: "16px",
                    height: "16px",
                    borderRadius: "4px",
                    border: "2px solid #999",
                    backgroundColor: isSelected ? "#000" : "#fff",
                    marginRight: "10px",
                    marginLeft: "0px",
                }}
            />
            <span style={{ fontSize: "14px", color: "#333", textAlign: "left" }}>{data.label}</span>
        </div>
    );
};

// ✅ React Select 스타일
const customStyles = {
    control: (provided) => ({
        ...provided,
        width: "100%",
        padding: "2px",
        height: "40px", // 높이 통일
        borderRadius: "4px",
        borderColor: "#ccc",
        boxShadow: "none",
        fontSize: "14px",
    }),
    option: (provided) => ({
        ...provided,
        padding: "8px 12px",
    }),
    menu: (provided) => ({
        ...provided,
        borderRadius: "4px",
        padding: "4px",
        boxShadow: "0 2px 8px rgba(0, 0, 0, 0.1)",
        zIndex: 10,
    }),
    menuList: (provided) => ({
        ...provided,
        textAlign: "left",
    }),
    multiValueLabel: (provided) => ({
        ...provided,
        color: "#000",
    }),
};



function GenreSelectDropdown({ genres, selectedGenres, setSelectedGenres }) {
    const options = genres.map((genre) => ({
        value: genre.id,
        label: genre.name,
    }));

    const handleChange = (selectedOptions) => {
        const ids = selectedOptions ? selectedOptions.map((opt) => opt.value) : [];
        setSelectedGenres(ids);
    };

    return (
        
        <Select
            isMulti
            options={options}
            closeMenuOnSelect={false}
            hideSelectedOptions={false}
            components={{ Option: CheckboxOption }}
            value={options.filter((o) => selectedGenres.includes(o.value))}
            onChange={handleChange}
            styles={customStyles}
            placeholder="선호 장르를 선택하세요"
        />
    );
}

export default GenreSelectDropdown;
