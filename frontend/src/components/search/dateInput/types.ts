import { Dayjs } from "dayjs";

export interface DateInputProps {
  label: string;
  date: Dayjs | null;
  onDateChange: (newDate: Dayjs | null) => void;
}
