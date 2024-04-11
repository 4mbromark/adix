import { FetchRecordResponse } from './../../domain/response.namespace';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Directive } from '@angular/core';
import { Subscription, BehaviorSubject, ReplaySubject, Observable } from 'rxjs';
import { BaseRecord } from 'src/app/domain/dto.namespace';

@Directive()
export abstract class BaseTableService<T extends BaseRecord> {
  protected url: string;

  // protected localPreference: TablePreference;

  // private tableSize = 100;
  // private infoSize = 0;

  // private subscription: Subscription;

  // private sidepanel: BehaviorSubject<RowClickedEvent> = new BehaviorSubject<RowClickedEvent>(null);

  private records: BehaviorSubject<T[]> = new BehaviorSubject<T[]>([]);
  private loading: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(true);

  constructor(
    // protected preferenceService: PreferenceService,
    protected readonly http: HttpClient
  ) {
    /* this.preferenceService.getPreference().subscribe((preferences: Preferences) => {
      this.localPreference = preferences.table;
    }); */
  }

  public getRecords(): Observable<T[]> {
    return this.records.asObservable();
  }

  public isLoading(): Observable<boolean> {
    return this.loading.asObservable();
  }

  /* public buildService(): void {
    this.subscription = this.projectService.getProjectIdWithSubprojects().subscribe((projects: number[]) => {
      this.loaded.next(false);
      if (projects === null) {
        this.fetch();
      } else if (projects.length !== 0) {
        this.getBy(projects);
      }
    });
  }
  public destroyService(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  } */

  public fetch(): Promise<void> {
    this.loading.next(true);

    return new Promise((resolve, reject) => {
      this.http.get(this.url, { responseType: 'json' }).subscribe(
        (response: FetchRecordResponse<T>) => {
          this.records.next(response.records);
          this.loading.next(false);
          resolve();
        },
        (error: HttpErrorResponse) => {
          reject(error);
        }
      );
    });
  }

  public add(record: T): Promise<void> {
    return new Promise((resolve, reject) => {
      this.http.post(this.url, record, { responseType: 'json' }).subscribe(
        () => {
          this.fetch();
          resolve();
        },
        (error: HttpErrorResponse) => {
          reject(error);
        }
      );
    });
  }

  public edit(record: T, id: number): Promise<void> {
    return new Promise((resolve, reject) => {
      this.http.put(this.url + ` /${id}`, record, { responseType: 'json' }).subscribe(
        () => {
          this.fetch();
          resolve();
        },
        (error: HttpErrorResponse) => {
          reject(error);
        }
      );
    });
  }

  public delete(id: number): Promise<void> {
    return new Promise((resolve, reject) => {
      this.http.delete(this.url + ` /${id}`).subscribe(
        () => {
          this.fetch();
          resolve();
        },
        (error: HttpErrorResponse) => {
          reject(error);
        }
      );
    });
  }

  /* protected updateTablePreference(table: TablePreference): void {
    this.preferenceService.updateTablePreference(table);
  } */

  /* getSidepanel(): Observable<RowClickedEvent> {
    return this.sidepanel.asObservable();
  } */

  /* openInformations(event?: RowClickedEvent): void {
    if (event) {
      this.sidepanel.next(event);
    }
    this.tableSize = 60;
    this.infoSize = 40;
  }
  openInformationsFull(): void {
    if (this.infoSize === 100) {
      this.openInformations();
    } else {
      this.tableSize = 0;
      this.infoSize = 100;
    }
  }
  closeInformations(): void {
    this.tableSize = 100;
    this.infoSize = 0;
    this.sidepanel.next(null);
  }
  getTableSize(): number {
    return this.tableSize;
  }
  getInfoSize(): number {
    return this.infoSize;
  }*/
}
