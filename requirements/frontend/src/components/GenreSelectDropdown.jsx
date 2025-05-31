import React from "react";
import Select, { components } from "react-select";

// 체크박스가 있는 Option 컴포넌트
const CheckboxOption = (props) => {
    return (
        <components.Option {...props}>
            <input
                type="checkbox"
                checked={props.isSelected}
                onChange={() => { }}
                style={{ marginRight: "8px" }}
            />
            {props.label}
        </components.Option>
    );
};

function GenreSelectDropdown({ genres, selectedGenres, setSelectedGenres }) {
    const options = genres.map((genre) => ({
        value: genre.id,
        label: genre.name,
    }));

    const handleChange = (selectedOptions) => {
        const ids = selectedOptions.map((opt) => opt.value);
        setSelectedGenres(ids);
    };

    return (
        <div>
            <label style={{ fontWeight: "bold" }}>🎯 선호 장르 선택</label>
            <Select
                isMulti
                options={options}
                closeMenuOnSelect={false}
                hideSelectedOptions={false}
                components={{ Option: CheckboxOption }}
                value={options.filter((o) => selectedGenres.includes(o.value))}
                onChange={handleChange}
                placeholder="장르를 선택하세요"
            />
        </div>
    );
}

export default GenreSelectDropdown;
