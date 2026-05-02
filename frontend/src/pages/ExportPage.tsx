import { Button } from '../components/ui/button';
import { Card, CardHeader, CardTitle, CardContent } from '../components/ui/card';
import { Download, FileJson, FileSpreadsheet } from 'lucide-react';

export default function ExportPage() {
  const exportFormats = [
    { format: 'CSV', icon: Download, description: 'Export data as CSV file' },
    { format: 'JSON', icon: FileJson, description: 'Export data as JSON file' },
    { format: 'Excel', icon: FileSpreadsheet, description: 'Export data as Excel file' },
  ];

  return (
    <div>
      <h1 className="text-2xl font-bold mb-6">Export Data</h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-8">
        {exportFormats.map((exp) => (
          <Card key={exp.format} className="cursor-pointer hover:bg-muted/50 transition-colors">
            <CardHeader className="text-center">
              <exp.icon className="h-12 w-12 mx-auto mb-4 text-primary" />
              <CardTitle className="text-lg">{exp.format}</CardTitle>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-sm text-muted-foreground">{exp.description}</p>
            </CardContent>
          </Card>
        ))}
      </div>

      <h2 className="text-lg font-semibold mb-4">Previous Exports</h2>
      <Card>
        <CardContent className="py-8 text-center text-muted-foreground">
          No exports yet
        </CardContent>
      </Card>
    </div>
  );
}