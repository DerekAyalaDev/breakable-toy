import { Box, TextField } from "@mui/material";
import { DatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { FormLabel } from "./FormLabel";
import dayjs, { Dayjs } from "dayjs";

interface DateInputProps {
  label: string;
  date: Dayjs | null;
  onDateChange: (newDate: Dayjs | null) => void;
}

export const DateInput = ({ label, date, onDateChange }: DateInputProps) => {
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
          value={date}
          onChange={onDateChange}
          minDate={minDate}
          maxDate={maxDate}
          slots={{
            textField: TextField,
          }}
          slotProps={{
            textField: {
              variant: "outlined",
              fullWidth: true,
              InputProps: {
                sx: {
                  "& .MuiSvgIcon-root": {
                    color: "var(--primary-color)",
                    fontSize: "1.5rem",
                    transition: "color 0.3s ease",
                  },
                  "&:hover .MuiSvgIcon-root": {
                    color: "var(--dark-primary-color)",
                  },
                },
              },
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
