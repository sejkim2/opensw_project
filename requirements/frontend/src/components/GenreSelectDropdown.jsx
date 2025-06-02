import React, { useEffect, useState } from "react";
import Select from "react-select";
import axios from "axios";

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


// 스타일 설정
const customStyles = {
    control: (provided) => ({
        ...provided,
        borderRadius: "10px",
        borderColor: "#ccc",
        boxShadow: "none",
    }),
    option: (provided) => ({
        ...provided,
        padding: 0,
    }),
    menu: (provided) => ({
        ...provided,
        borderRadius: "10px",
        padding: "6px",
        boxShadow: "0 4px 10px rgba(0, 0, 0, 0.1)",
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

function GenreSelectDropdown() {
    const [genres, setGenres] = useState([]);
    const [selectedGenres, setSelectedGenres] = useState([]);

    // 장르 목록 및 사용자 선택값 불러오기
    useEffect(() => {
        const fetchData = async () => {
            try {
                const genreRes = await axios.get("/api/genres");
                setGenres(genreRes.data);

                const selectedRes = await axios.get("/api/user-genres");
                setSelectedGenres(selectedRes.data.map((g) => g.id));
            } catch (error) {
                console.error("데이터 불러오기 오류:", error);
            }
        };

        fetchData();
    }, []);

    const options = genres.map((genre) => ({
        value: genre.id,
        label: genre.name,
    }));

    const handleChange = async (selectedOptions) => {
        const ids = selectedOptions.map((opt) => opt.value);
        setSelectedGenres(ids);

        try {
            await axios.post("/api/user-genres", { genreIds: ids });
        } catch (error) {
            console.error("서버 저장 실패:", error);
        }
    };

    return (
        <div style={{ textAlign: "center" }}>
            <label style={{ fontWeight: "bold" }}>🎯 선호 장르 선택</label>
            <div style={{ display: "inline-block", width: "300px" }}>
                <Select
                    isMulti
                    options={options}
                    closeMenuOnSelect={false}
                    hideSelectedOptions={false}
                    components={{ Option: CheckboxOption }}
                    value={options.filter((o) => selectedGenres.includes(o.value))}
                    onChange={handleChange}
                    styles={customStyles}
                    placeholder="장르를 선택하세요"
                />
            </div>
        </div>
    );
}

export default GenreSelectDropdown;
