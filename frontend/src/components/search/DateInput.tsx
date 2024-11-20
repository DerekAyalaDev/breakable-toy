import { Box, TextField } from "@mui/material";
import { DatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { useState } from "react";
import { FormLabel } from "./FormLabel";
import dayjs, { Dayjs } from "dayjs";

interface DateInputProps {
  label: string;
}

export const DateInput = ({ label }: DateInputProps) => {
  const [selectedDate, setSelectedDate] = useState<Dayjs | null | undefined>(
    null
  );
  const minDate = dayjs();
  const maxDate = dayjs().add(2, "year");
  return (
    <Box
      sx={{
        marginBottom: "1rem",
        display: "flex",
        alignItems: "center",
        gap: "1rem",
      }}
    >
      <FormLabel label={label} />
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <DatePicker
          value={selectedDate}
          onChange={(newValue) => setSelectedDate(newValue)}
          minDate={minDate}
          maxDate={maxDate}
          slots={{
            textField: TextField,
          }}
          slotProps={{
            textField: {
              variant: "outlined",
              fullWidth: true,
              sx: {
                flex: "3",
                "& .MuiOutlinedInput-root": {
                  "& fieldset": {
                    borderColor: "var(--secondary-color)",
                    transition: "border-color 0.3s ease",
                  },
                  "&:hover fieldset": {
                    borderColor: "var(--primary-color)",
                  },
                  "&.Mui-focused fieldset": {
                    borderColor: "var(--dark-primary-color)",
                  },
                },
              },
            },
          }}
        />
      </LocalizationProvider>
    </Box>
  );
};
